package solutions.studying.led;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

public class NoticeDialogFragment extends DialogFragment {
    /* The activity that creates an instance of this dialog fragment must
    /* implement this interface in order to receive event callbacks.
    /* Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String ip, String user, String Password, String ledName);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private EditText txtIpAddress;
    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtLedName;

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;

        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());




        builder.setView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_get_ip, null))
                .setPositiveButton(R.string.connect, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        txtIpAddress = getDialog().findViewById(R.id.ip_input);
                        txtUsername = getDialog().findViewById(R.id.username_input);
                        txtPassword = getDialog().findViewById(R.id.password_input);
                        txtLedName = getDialog().findViewById(R.id.led_name_input);
                        mListener.onDialogPositiveClick(NoticeDialogFragment.this, txtIpAddress.getText().toString(), txtUsername.getText().toString(),
                                txtPassword.getText().toString(), txtLedName.getText().toString());

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        mListener.onDialogNegativeClick(NoticeDialogFragment.this);
                    }
                })
                .setTitle("Add LED Strip");
        return builder.create();
    }
}
