package xq.yiesko.app180go.fragments.home.pages.calc.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalcViewModel extends ViewModel {
    private final MutableLiveData<Double> baseFare = new MutableLiveData<>(5.0);
    private final MutableLiveData<Double> kmRate = new MutableLiveData<>(2.0);
    private final MutableLiveData<Double> timeRate = new MutableLiveData<>(0.5);
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
                ? kmRate.getValue() : 2.0;
        var timeR = timeRate.getValue()
                != null
                ? timeRate.getValue() : 0.5;

        var total = base + (km * kmR) + (minutes * timeR);

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
}