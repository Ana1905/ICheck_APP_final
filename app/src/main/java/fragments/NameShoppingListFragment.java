package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icheck_app_final.R;
import com.example.icheck_app_final.SelectProductsActivity;
import com.example.icheck_app_final.SettingsActivity;
import com.example.icheck_app_final.SignUpActivity;


public class NameShoppingListFragment extends Fragment {
    Button mContinue;
    EditText mEditTextNameList;

    public static NameShoppingListFragment newInstance() {
        return new NameShoppingListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_name_shopping_list, container, false);
        Toast.makeText(view.getContext(), "Please long press the key", Toast.LENGTH_LONG ).show();
        mContinue = view.findViewById(R.id.ButtonContinueList);
        mEditTextNameList= view.findViewById(R.id.EditTextNameList);

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "mandando", Toast.LENGTH_LONG ).show();
                Intent intent = new Intent(getActivity(), SelectProductsActivity.class);
                startActivity(intent);
            }
        });

                // Inflate the layout for this fragment
        return view;
    }

}

