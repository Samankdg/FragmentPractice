package com.codepath.fragmentpractice;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.codepath.fragmentpractice.fragments.TODODetailsFragment;
import com.codepath.fragmentpractice.fragments.TODOMenuFragment;

public class MainActivity extends AppCompatActivity  implements TODOMenuFragment.OnItemSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      // Instance of first fragment
      TODOMenuFragment firstFragment = new TODOMenuFragment();

      // Add Fragment to FrameLayout (flContainer), using FragmentManager
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
      ft.add(R.id.flContainer, firstFragment);                                // add    Fragment
      ft.commit();                                                            // commit FragmentTransaction
    }
  }

  @Override
  public void onTodoItemSelected(int position) {
    Toast.makeText(this, "Called By Fragment A: position - "+ position, Toast.LENGTH_SHORT).show();

    // Load TODO Detail Fragment
    TODODetailsFragment secondFragment = new TODODetailsFragment();

    Bundle args = new Bundle();
    args.putInt("position", position);
    secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle

    getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flContainer, secondFragment) // replace flContainer
          .addToBackStack(null)
          .commit();
  }
}
