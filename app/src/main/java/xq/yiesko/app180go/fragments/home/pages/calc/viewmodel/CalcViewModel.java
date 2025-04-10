package xq.yiesko.app180go.fragments.home.pages.calc.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalcViewModel extends ViewModel {
    private final MutableLiveData<Double> baseFare = new MutableLiveData<>(5.0);
    private final MutableLiveData<Double> kmRate = new MutableLiveData<>(1.5);
    private final MutableLiveData<Double> timeRate = new MutableLiveData<>(0.35);
    private final MutableLiveData<Double> discountRate = new MutableLiveData<>(5.0);
    private final MutableLiveData<Double> result = new MutableLiveData<>();

    public void calculate(
            double km,
            double minutes
    ) {
        var base = baseFare.getValue()
                != null
                ? baseFare.getValue() : 5.0;
        var kmR = kmRate.getValue()
                != null
                ? kmRate.getValue() : 1.5;
        var timeR = timeRate.getValue()
                != null
                ? timeRate.getValue() : 0.35;
        var discount = discountRate.getValue()
                != null
                ? discountRate.getValue() : 5.0;

        var subtotal = base + (km * kmR) + (minutes * timeR);
        var total = subtotal * (1 - discount / 100);

        result.setValue(total);
    }

    public LiveData<Double> getResult() {
        return result;
    }

    public void setBaseFare(
            double value
    ) {
        baseFare.setValue(value);
    }

    public void setKmRate(
            double value
    ) {
        kmRate.setValue(value);
    }

    public void setTimeRate(
            double value
    ) {
        timeRate.setValue(value);
    }

    public void setDiscountRate(
            double value
    ) {
        discountRate.setValue(value);
    }
}