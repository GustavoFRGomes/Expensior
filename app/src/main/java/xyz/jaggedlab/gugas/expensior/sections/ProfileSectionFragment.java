package xyz.jaggedlab.gugas.expensior.sections;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import xyz.jaggedlab.gugas.expensior.R;
import xyz.jaggedlab.gugas.expensior.model.Expense;
import xyz.jaggedlab.gugas.expensior.sections.profile.IOnProfileItemClicked;
import xyz.jaggedlab.gugas.expensior.sections.profile.ProfileItem;
import xyz.jaggedlab.gugas.expensior.sections.profile.ProfileSectionFragmentAdapter;
import xyz.jaggedlab.gugas.expensior.sections.profile.ResetDataDialogFragment;
import xyz.jaggedlab.gugas.expensior.utils.Constants;

public class ProfileSectionFragment extends Fragment implements IOnProfileItemClicked, ResetDataDialogFragment.IResetDataActionListener{

    private RecyclerView profileItemRecycler;

    public ProfileSectionFragment() {
        // Required empty public constructor
    }

    public static ProfileSectionFragment newInstance() {
        ProfileSectionFragment fragment = new ProfileSectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_section, container, false);
        // Inflate the layout for this fragment

        this.profileItemRecycler = ((RecyclerView) rootView.findViewById(R.id.profile_section_recycler_view));
        this.profileItemRecycler.setAdapter(new ProfileSectionFragmentAdapter(this.getContext(), this));
        this.profileItemRecycler.setHasFixedSize(true);
        this.profileItemRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return rootView;
    }

    @Override
    public void onProfileItemClicked(View view, ProfileItem profileItem) {
        Toast.makeText(this.getContext(), profileItem.toString() + " was clicked on!", Toast.LENGTH_LONG).show();

        switch (profileItem) {
            case FEEDBACK:
                this.launchEmailClient();
                break;
            case IMPORT_DATA:
                this.importSingleExpenseFromJson("qwerty");
                break;
            case RESET_DATA:
                this.presentResetConfirmationDialog();
                break;
            case EXPORT_DATA:
                //this.exportSingleExpenseToJson();
                break;
            case LOGOUT:
            default:
                break;
        }
    }

    private void presentResetConfirmationDialog() {
        // Present the confirmation dialog to ensure the user wants to really delete the entire expenses entries.
        ResetDataDialogFragment.newInstance(this).show(getFragmentManager());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    private void resetDataFromRealm() {
        // FIXME: Uncomment this once the project is merge with the main branch.
        /*
            final RealmResults<Expense> results = this.realmInstance.where(Expense.class).findAll();

            realmInstance.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteAllFromRealm(); // deletes every object in results.
                }
            });
         */
    }

    private void importSingleExpenseFromJson(String singleJsonExpense) {
        /*
        this.realmInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObjectFomJson(Expense.class, singleJsonExpense);
            }
        });
         */
    }

    private void exportSingleExpenseToJson(Expense expenseToExport) {

    }

    private void launchEmailClient() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse(Constants.EMAIL_TO_FOR_INTENT));
        emailIntent.setType(Constants.EMAIL_TYPE_FOR_INTENT);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {Constants.EMAIL_ADDRESS});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Constants.EMAIL_SUBJECT_FEEDBACK);
        this.getActivity().startActivity(Intent.createChooser(emailIntent, Constants.EMAIL_CHOOSER_STRING));
    }

    @Override
    public void onPositiveButtonPressed(DialogFragment dialog) {
        Toast.makeText(this.getContext(), "Yes Button Clicked", Toast.LENGTH_LONG).show();
        dialog.dismissAllowingStateLoss();
    }

    @Override
    public void onNegativeButtonPressed(DialogFragment dialog) {
        dialog.dismissAllowingStateLoss();
    }
}
