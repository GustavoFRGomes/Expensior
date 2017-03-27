package xyz.jaggedlab.gugas.expensior.sections.profile;

/**
 * Created by Asus on 27/03/2017.
 */

public enum ProfileItem {
    IMPORT_DATA(0),
    EXPORT_DATA(1),
    RESET_DATA(2),
    LOGOUT(3),
    FEEDBACK(4);

    public static int getTotalNumberOfItems() {
        return 5;
    }

    public static ProfileItem getProfileItemFromNumber(int number) {
        final int itemNumber = number;

        switch (itemNumber) {
            case 0:
                return IMPORT_DATA;
            case 1:
                return EXPORT_DATA;
            case 2:
                return RESET_DATA;
            case 3:
                return LOGOUT;
            default:
            case 4:
                return FEEDBACK;

        }
    }

    private ProfileItem(int itemOrder) {
        this.itemOrder = itemOrder;
    }

    private final int itemOrder;
    private int getItemOrder() {
        return this.itemOrder;
    }
}
