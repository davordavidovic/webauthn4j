package com.webauthn4j.validator;

import com.webauthn4j.data.client.Origin;
import com.webauthn4j.data.payment.CollectedClientAdditionalPaymentData;
import com.webauthn4j.data.payment.PaymentAuthenticationParameters;
import com.webauthn4j.util.AssertUtil;
import com.webauthn4j.validator.exception.BadOriginException;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PaymentOriginValidatorImpl implements PaymentOriginValidator {

    @Override
    public void validate(@NonNull CollectedClientAdditionalPaymentData clientAdditionalPaymentData, @NonNull PaymentAuthenticationParameters paymentAuthenticationParameters) {
        AssertUtil.notNull(clientAdditionalPaymentData, "collectedClientAdditionalPaymentData must not be null");
        AssertUtil.notNull(paymentAuthenticationParameters, "serverProperty must not be null");

        Origin payeeOrigin = clientAdditionalPaymentData.getPayeeOrigin();
        Origin topOrigin = clientAdditionalPaymentData.getTopOrigin();
        AssertUtil.notNull(payeeOrigin, "payeeOrigin in collectedClientAdditionalPaymentData must not be null");
        AssertUtil.notNull(topOrigin, "topOrigin in collectedClientAdditionalPaymentData must not be null");

        if (!paymentAuthenticationParameters.getServerProperty().getOrigins().contains(topOrigin)) {
            throw new BadOriginException("The collectedClientAdditionalPaymentData '" + topOrigin + "' top origin doesn't match any of the preconfigured origins.");
        }

        if (!paymentAuthenticationParameters.getPayeeOrigins().contains(payeeOrigin)) {
            throw new BadOriginException("The collectedClientAdditionalPaymentData '" + payeeOrigin + "' payee origin doesn't match any of the preconfigured origins.");
        }

    }

}
