package meugeninua.mediasample.ui.fragments.base.state;

import android.os.Bundle;

public abstract class BaseStateImpl implements State {

    protected Bundle bundle;

    @Override
    public void attachBundle(Bundle bundle) {
        this.bundle = bundle == null ? Bundle.EMPTY : bundle;
    }

    @Override
    public void detachBundle() {
        this.bundle = null;
    }
}
