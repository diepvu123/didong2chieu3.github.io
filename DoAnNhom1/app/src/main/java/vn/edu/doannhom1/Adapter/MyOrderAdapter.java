package vn.edu.doannhom1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import vn.edu.doannhom1.Model.MyOrders;
import vn.edu.doannhom1.R;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<MyOrders> list;
    String from;

    public MyOrderAdapter(Context context, ArrayList<MyOrders> list,String from) {
        this.context = context;
        this.list = list;
        this.from = from;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_order,
                parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyOrders model = list.get(position);

        holder.orderId.setText("#"+model.getOrderId());
        //holder.oderDate.setText(model.getOrderTime());
        holder.price.setText(model.getPrice()+"VND");
        holder.status.setText(model.getStatus());

        if (from.equals("customer")){
            //holder.deliveryDate.setVisibility(View.GONE);
            holder.btnFinish.setVisibility(View.GONE);
        }else {
            //holder.deliveryDate.setVisibility(View.VISIBLE);
            holder.btnFinish.setVisibility(View.VISIBLE);

            //holder.deliveryDate.setText("Delivery Time: "+model.getDeliveryTime());
            holder.btnFinish.setOnClickListener(view -> {
                String id = model.getOrderId();

                HashMap<String,Object> map = new HashMap<>();
                map.put("status","Đã Giao Hàng");

                FirebaseDatabase.getInstance().getReference().child("Orders")
                        .child(id)
                        .updateChildren(map)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "Đơn hàng đang giao", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Không thể giao đơn hàng", Toast.LENGTH_SHORT).show();

                            }
                        });


            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId,oderDate,price,status,btnFinish,deliveryDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
            //oderDate = itemView.findViewById(R.id.orderDate);
            price = itemView.findViewById(R.id.itemPrice);
            status = itemView.findViewById(R.id.status);
            //deliveryDate = itemView.findViewById(R.id.deliveryDate);
            btnFinish = itemView.findViewById(R.id.btnFinish);



        }
    }
}
