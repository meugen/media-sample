package meugeninua.mediasample.ui.activities.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import meugeninua.mediasample.R;
import meugeninua.mediasample.ui.fragments.player.PlayerFragment;

public class PlayerActivity extends AppCompatActivity {

    private static final String ARG_POSITION = "position";
    private static final String TAG_PLAYER_FRAGMENT = "player";

    public static void start(final Context context, final int position) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(ARG_POSITION, position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(TAG_PLAYER_FRAGMENT) == null) {
            int position = getIntent().getIntExtra(ARG_POSITION, 0);
            fragmentManager.beginTransaction()
                    .add(R.id.container, PlayerFragment.build(position))
                    .commit();
        }
    }
}
