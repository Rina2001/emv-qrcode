package com.x4fare.emvqrcode;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasicLibraryTest {

    @Test
    public void test() {
        final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
        final MerchantAccountInformationTemplate merchantAccountInformation = getMerchanAccountInformation();
        final MerchantInformationLanguageTemplate merchantInformationLanguage = getMerchantInformationLanguage();
        final UnreservedTemplate unreserved = getUnreserved();
        final TagLengthString rFUforEMVCo = new TagLengthString("65", "00");

        final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
        merchantPresentMode.setAdditionalDataField(additionalDataField);
        merchantPresentMode.setCountryCode("CN");
        merchantPresentMode.setMerchantCategoryCode("4111");
        merchantPresentMode.setMerchantCity("BEIJING");
        merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
        merchantPresentMode.setMerchantName("BEST TRANSPORT");
        merchantPresentMode.setPayloadFormatIndicator("01");
        merchantPresentMode.setPointOfInitiationMethod("11");
        merchantPresentMode.setPostalCode("1234567");
        merchantPresentMode.setTipOrConvenienceIndicator("02");
        merchantPresentMode.setTransactionAmount("23.72");
        merchantPresentMode.setTransactionCurrency("156");
        merchantPresentMode.setValueOfConvenienceFeeFixed("500");
        merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
        merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
        merchantPresentMode.addUnreserved(unreserved);

        String mpmResult = merchantPresentMode.toString();
        System.out.println(mpmResult);
        assertThat(mpmResult, equalTo("00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klmno0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863046325"));
    }

    private MerchantAccountInformationTemplate getMerchanAccountInformation() {
        final TagLengthString paymentNetworkSpecific = new TagLengthString();
        paymentNetworkSpecific.setTag("01");
        paymentNetworkSpecific.setValue("abcd");

        final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
        merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
        merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

        final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
        merchantAccountInformation.setValue(merchantAccountInformationValue);
        merchantAccountInformation.setTag("02");

        return merchantAccountInformation;
    }

    private UnreservedTemplate getUnreserved() {
        final TagLengthString contextSpecificData = new TagLengthString();
        contextSpecificData.setTag("07");
        contextSpecificData.setValue("12345678");

        final Unreserved value = new Unreserved();
        value.setGloballyUniqueIdentifier("A011223344998877");
        value.addContextSpecificData(contextSpecificData);

        final UnreservedTemplate unreserved = new UnreservedTemplate();
        unreserved.setValue(value);
        unreserved.setTag("80");

        return unreserved;
    }

    private MerchantInformationLanguageTemplate getMerchantInformationLanguage() {
        final TagLengthString rFUforEMVCo = new TagLengthString();
        rFUforEMVCo.setTag("03");
        rFUforEMVCo.setValue("abcd");

        final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
        merchantInformationLanguageValue.setLanguagePreference("ZH");
        merchantInformationLanguageValue.setMerchantName("北京");
        merchantInformationLanguageValue.setMerchantCity("最佳运输");
        merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

        final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
        merchantInformationLanguage.setValue(merchantInformationLanguageValue);

        return merchantInformationLanguage;
    }

    private AdditionalDataFieldTemplate getAddtionalDataField() {
        final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
        paymentSystemSpecific.setGloballyUniqueIdentifier("1234");
        paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "ijkl"));

        final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
        paymentSystemSpecificTemplate.setTag("50");
        paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

        final TagLengthString rFUforEMVCo = new TagLengthString();
        rFUforEMVCo.setTag("10");
        rFUforEMVCo.setValue("abcd");

        final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
        additionalDataFieldValue.setAdditionalConsumerDataRequest("tuv");
        additionalDataFieldValue.setMobileNumber("67890");
        additionalDataFieldValue.setPurposeTransaction("pqres");
        additionalDataFieldValue.setReferenceLabel("abcde");
        additionalDataFieldValue.setStoreLabel("09876");
        additionalDataFieldValue.setTerminalLabel("klmno");
        additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);
        additionalDataFieldValue.addRFUforEMVCo(rFUforEMVCo);

        final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
        additionalDataField.setValue(additionalDataFieldValue);

        return additionalDataField;
    }
}
