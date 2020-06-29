package Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.icheck_app_final.R;


public class StorePickerFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    //variables
    private Spinner mSpinner;
    private CheckBox mCheckbox;
    public static final String EXTRA_CITY = "extraCity";

    private String mCity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_stores_prices, null);
        //wiring up

        //para poner lo delen el arreglo de items



        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.Stores)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCheckbox.isChecked()) {

                        }
                        sendResult(Activity.RESULT_OK, mCity);
                    }
                })
                .create();
    }
    //cuando se selecciona una ciudad (item) la toma y pone en mCity
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mCity = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static StorePickerFragment newCityPickerInstance(){
        return new StorePickerFragment();
    }

    private void sendResult(int resultCode, String mCity){
        if(getTargetFragment()==null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CITY, mCity);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}