package xyz.jaggedlab.gugas.expensior.sections.profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.sections.ProfileSectionFragment;

/**
 * Created by Asus on 27/03/2017.
 */

public class ResetDataDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "RESET_DIALOG_TAG";

    public interface IResetDataActionListener {
        public void onPositiveButtonPressed(DialogFragment dialog);
        public void onNegativeButtonPressed(DialogFragment dialog);
    }

    protected IResetDataActionListener actionListener;
    private View rootView;

    public static ResetDataDialogFragment newInstance(IResetDataActionListener resetListener) {
        ResetDataDialogFragment fragment = new ResetDataDialogFragment();
        fragment.actionListener = resetListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        this.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ConfirmationDialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setWindowAnimations(R.style.ConfirmationDialogAnimation);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.reset_data_confirmation_dialog, container, false);

        ((TextView) this.rootView.findViewById(R.id.confirmation_title)).setText(R.string.warning);
        ((TextView) this.rootView.findViewById(R.id.confirmation_message)).setText(R.string.are_you_sure_text);

        ((Button) this.rootView.findViewById(R.id.positive_button)).setOnClickListener(this);
        ((Button) this.rootView.findViewById(R.id.negative_button)).setOnClickListener(this);

        return this.rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.negative_button:
                if (isClickListenerValid()) {
                    this.actionListener.onNegativeButtonPressed(this);
                }
                break;
            case R.id.positive_button:
                if (isClickListenerValid()) {
                    this.actionListener.onPositiveButtonPressed(this);
                }
                break;
        }
    }

    private boolean isClickListenerValid() {
        return this.actionListener != null;
    }

    public ResetDataDialogFragment setResetClickListeners(IResetDataActionListener resetActionListeners) {
        this.actionListener = resetActionListeners;
        return this;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, ResetDataDialogFragment.TAG);
    }

}
