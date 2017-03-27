package xyz.jaggedlab.gugas.expensior.sections.profile;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.jaggedlab.gugas.expensior.R;

/**
 * Created by Asus on 27/03/2017.
 */

public class ProfileSectionFragmentAdapter extends RecyclerView.Adapter {

    private IOnProfileItemClicked onProfileItemClicked;
    private Context context;

    public ProfileSectionFragmentAdapter(Context context, IOnProfileItemClicked onProfileItemClicked) {
        this.context = context;
        this.onProfileItemClicked = onProfileItemClicked;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        final ProfileItem profileItem = ProfileItem.getProfileItemFromNumber(viewType);

        View itemView = LayoutInflater.from(this.context).inflate(R.layout.profile_list_item_layout, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileSectionFragmentAdapter.this.onProfileItemClicked.onProfileItemClicked(view, profileItem);
            }
        });
        return new ProfileItemViewHolder(itemView, profileItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProfileItem profileItem = ProfileItem.getProfileItemFromNumber(position);

        String itemTitle;
        switch (profileItem) {
            case IMPORT_DATA:
                itemTitle = this.context.getString(R.string.import_data);
                break;
            case EXPORT_DATA:
                itemTitle = this.context.getString(R.string.export_data);
                break;
            default:
            case FEEDBACK:
                itemTitle = this.context.getString(R.string.give_us_feedback);
                break;
            case LOGOUT:
                itemTitle = this.context.getString(R.string.log_out);
                break;
            case RESET_DATA:
                itemTitle = this.context.getString(R.string.reset_data);
                break;
        }

        ((ProfileItemViewHolder) holder).itemName.setText(itemTitle);
    }

    @Override
    public int getItemCount() {
        return ProfileItem.getTotalNumberOfItems(); // It is the ProfileItem enum.
    }

    @Override
    public int getItemViewType(int position) {
        return position; // @ the onCreateViewHolder the correct item will be inferred from the position;
    }

    public static class ProfileItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public ProfileItem profileItem;

        public ProfileItemViewHolder(View view, ProfileItem profileItem) {
            super(view);

            // Link the itemName
            this.itemName = ((TextView) view.findViewById(R.id.profile_list_item_title));
            this.profileItem = profileItem;
        }
    }
}