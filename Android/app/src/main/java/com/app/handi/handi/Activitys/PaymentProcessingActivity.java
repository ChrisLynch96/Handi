package com.app.handi.handi.Activitys;

/**
 * Created by richi on 18/03/2017.
 */

import com.app.handi.handi.R;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;

public class PaymentProcessingActivity {

    CardInputWidget mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);
    Card cardToSave = mCardInputWidget.getCard();
    if (cardToSave == null) {
        mErrorDialogHandler.showError("Invalid Card Data");
    }
}
