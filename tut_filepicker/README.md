# Sasha's-file-picker
## What Am establishing ? 
- [x] start new activity on button click . this activity will be having a recycler view to display the selected pics and a fab to start the piture picker intent
- [x] start a picture picker intent on fab click (floating action button)

### *Lets go.*

1. In your `MainActivity` class ( the one with 3 long buttons (prescription, reports ... ) : write the following code :

```java
...
    OnCreate(...){
       Button btn = findViewById(R.id.yourBtn); // those long buttons( prescription , report , etc)
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        
   }
   ...
}
```

2. in your SecondActivity , the layout should be something like this : 
```xml

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/grey_light"
    tools:context="com.work.chaostools.jimsadmin.MActivity">
    
    
    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_main_l"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        
        />


    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab_add"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|right"
        android:layout_margin="5dp"
        
        app:backgroundTint="@color/green_fab"
        app:fabSize="normal"
        android:scaleType="center"
        android:contentDescription="press this button to add images"
        android:src="@drawable/ic_add"     
        />
</FrameLayotayout>

```
Your Second activity will look something like this :

```java

public class SecondActivity extends AppCompatActivity {
    MyAdapter adaptr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        
        RecyclerView rv =findViewById(R.id.rv_main_l);
        adaptr = new MyAdapter(new ArrayList<>());; adapter initialised with empty array list
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adaptr); // we added a simple empty arraylist to our adapter . ( we will come back to adapter's code in a minute) 

        FloatingActionButton btn =findViewById(R.id.fab_add);
       ...
    }
}

```

for this fab button , there will be a typical flow on its `onClick`
 - usr clicks the button , therefore its `onclicklistener` gets triggerred .
 - Inside its onclicklistener, we simply start a system's internal activity to get an image from the its build in file picker.
 - It wont be giving a png or jpg image , but a uri , which is like an address of the image in file system ( eg `" file://Sdcard_0/Camera/DCIM/facebook/29July2018.png"` )
 - you can simply update your adapter with this uri, which will automagically load the image from that location( not really magically, its just picasso under the hood.
 - now thw question comes ***where do we get this uri??** the answer is inside the `onactivityresult()` . you will recieve the uri in the form of the intent's extra data.
 
 here is the code for it:  
 
 ```java
 
    MyAdapter adp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    ...
        
        FloatingActionButton btn =findViewById(R.id.fab_send);//fabadd
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(Intent.ACTION_GET_CONTENT); //necessary for system to cal its file picker
                i2.setType("image/*");//necessary for system to cal its file picker
                startActivityForResult(Intent.createChooser(i2, "Complete Action Using:"), 123);// will create a chooser if multiple apps are available els open system's build in file picker
                
                //remember this number '123' , this will be used to recieve data
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 123 && resultCode == RESULT_OK){
            Uri data_uri = intent.getData(); // nice!, you got  the data uri here!
            adp.addNewImage(data_uri); // you will be understanding this function in the next step
        }
    }
       
}
 
 ```
 
 
 So we now know the how to getimage from the sytem. let's build the data structure and Adapter for it.  
 
 dont forget to add picasso library in gradle  :
 ```
    implementation 'com.squareup.picasso:picasso:2.71828' 
 ```
 
 
 Our data structure won't be too tough, just a simple class with a single `Uri` object .
 ```java
   public  class RvData{
        private Uri picUri;

        public RvData(Uri picUri) {
            this.picUri = picUri;
        }

        public Uri getPicUri() {
            return picUri;
        }

        public void setPicUri(Uri picUri) {
            this.picUri = picUri;
        }
    } 
 ```
 Next comes the adapter part . but first , its single row layout layout :  
 rv_eachrow.xml
 ```xml
 
 <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:android="http://schemas.android.com/apk/res/android">
<ImageView
    android:id="@+id/iv_pic"
    android:layout_width="match_parent"
    android:layout_height="180dp" /><!--I gave a fixed height, you could also give wrp_content or match_parent-->

</androidx.constraintlayout.widget.ConstraintLayout>
 
 ```
 your holder will be :
 ```java
    public  class myHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.iv_pic);
        }
    }
 
 ```
 
 and your Adapter will be
 ```java
 
 public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myHolder>{
    ArrayList<RvData> dataArr;

    public MyAdapter(ArrayList<RvData> dataArr) {
        this.dataArr = dataArr;
    }

    @NonNull @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_eachrow,parent,false);
        
        return new myHolder(v);
        
        //note , i use my old ways( i am a 90 yr old soul trapped in a 20 yr old body :P ) in this adapter
        // you might use your own ways, its just a simple adapter inflating a view
        
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        //IMPORTANT!!!
        Picasso.get().load(dataArr.get(position).getPicUri()).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return dataArr.size();
    }

    public void addNewImage(Uri uri){//Notice this will be an important  function to be used when adding new URis
        dataArr.add(new RvData(uri));
        notifyDataSetChanged();
        
    }


    public  class myHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.iv_pic);
        }
    }

 
 ```
 
maybe you would like to have a 'next' button too , like this :  
![](https://imgur.com/s1UwdTM.png)



