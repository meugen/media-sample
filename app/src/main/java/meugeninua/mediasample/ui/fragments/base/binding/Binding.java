package meugeninua.mediasample.ui.fragments.base.binding;

import android.view.View;

import meugeninua.mediasample.ui.fragments.base.state.State;

public interface Binding<S extends State> {

    void attachView(View view);

    void detachView();

    <V extends View> V get(int id);

    boolean has(int id);

    void saveState(S state);

    void restoreState(S state);
}
