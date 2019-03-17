package meugeninua.mediasample.ui.fragments.playlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import meugeninua.mediasample.R;
import meugeninua.mediasample.model.db.entities.MediaEntity;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlayitemHolder> {

    private final LayoutInflater inflater;
    private List<MediaEntity> entities;

    public PlaylistAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        entities = Collections.emptyList();
    }

    public void swapEntities(List<MediaEntity> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayitemHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = inflater.inflate(R.layout.play_item, parent, false);
        return new PlayitemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayitemHolder holder, final int position) {
        holder.bind(entities.get(position));
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    static class PlayitemHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;
        private final TextView descriptionView;

        PlayitemHolder(@NonNull final View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.entity_name);
            descriptionView = itemView.findViewById(R.id.entity_description);
        }

        void bind(MediaEntity entity) {
            nameView.setText(entity.name);
            descriptionView.setText(entity.description);
        }
    }
}
