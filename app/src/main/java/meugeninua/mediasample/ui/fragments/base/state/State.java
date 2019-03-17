package meugeninua.mediasample.ui.fragments.base.state;

import android.os.Bundle;

public interface State {

    State EMPTY = new BaseStateImpl() { };

    void attachBundle(Bundle bundle);

    void detachBundle();
}
