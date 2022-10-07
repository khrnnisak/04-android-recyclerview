package jti.nisak.a04androidrecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    private ArrayList<String> namaMakanan = new ArrayList<>();
    private ArrayList<String> descMakanan = new ArrayList<>();
    private ArrayList<String> fotoMakanan = new ArrayList<>();
    private ArrayList<String> bahanMakanan = new ArrayList<>();
    private ArrayList<String> caraMembuat = new ArrayList<>();
    private final Context context;
    private View.OnClickListener mOnClick;

    public RecipeListAdapter(ArrayList<String> namaMakanan, ArrayList<String> descMakanan, ArrayList<String> fotoMakanan, ArrayList<String> bahanMakanan, ArrayList<String> caraMembuat, Context context) {
        this.namaMakanan = namaMakanan;
        this.descMakanan = descMakanan;
        this.fotoMakanan = fotoMakanan;
        this.bahanMakanan = bahanMakanan;
        this.caraMembuat = caraMembuat;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_design_adapter,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).asBitmap().load(fotoMakanan.get(position)).into(holder.profileImage);
        holder.recipeMenu.setText(namaMakanan.get(position));
        holder.recipeDescription.setText(descMakanan.get(position));
        holder.constraintLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("nama_makanan", namaMakanan.get(position));
            intent.putExtra("foto_makanan", fotoMakanan.get(position));
            intent.putExtra("bahan_makanan", bahanMakanan.get(position));
            intent.putExtra("cara_membuat", caraMembuat.get(position));

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return namaMakanan.size();
    }

    public void setmOnClick(View.OnClickListener onItemClickListener){
        mOnClick = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView profileImage;
        TextView recipeMenu;
        TextView recipeDescription;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            recipeMenu = itemView.findViewById(R.id.recipeMenu);
            recipeDescription = itemView.findViewById(R.id.recipeDescription);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            itemView.setOnClickListener(mOnClick);
        }
    }

}
