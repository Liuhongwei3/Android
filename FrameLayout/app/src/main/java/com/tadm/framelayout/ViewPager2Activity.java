//package com.tadm.framelayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ViewPager2Activity extends AppCompatActivity {
//    private ViewPager viewpager;
//    private LinearLayout layout;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_pager2);
//        initViews();
//        //viewpager实现自动轮播需要通过handler
//    }
//
//    private void initViews() {
//        layout=findViewById(R.id.layout);
//        viewpager = (ViewPager) findViewById(R.id.viewpager);
//        //准备好Viewpager中显示的页面数据（图片、布局文件等）
//        int[]imgs={R.drawable.shizi,R.drawable.mojie,R.drawable.juxie,
//                R.drawable.jinniu,R.drawable.chunv};
//        View view=getLayoutInflater().inflate(R.layout.gamelist_item,null);
//        final List<View> pages=new ArrayList<>();
//        for (int i = 0; i < imgs.length; i++) {
//            ImageView img=new ImageView(ViewPager2Activity.this);
//            img.setImageResource(imgs[i]);
//            pages.add(img);
//        }
//        pages.add(view);
//        viewpager.setAdapter(new MyNewPagerAdapter(pages));
//        int id=Integer.MAX_VALUE-Integer.MAX_VALUE%pages.size();
//        viewpager.setCurrentItem(id);
//
//        //动态添加导航点到LinearLayout中
//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(15,15);
//        params.setMargins(8,0,8,0);
//        for (int i = 0; i <pages.size(); i++) {
//            ImageView img=new ImageView(ViewPager2Activity.this);
//            if(i==0)
//                img.setImageResource(R.drawable.page_on);
//            else
//                img.setImageResource(R.drawable.page_off);
//            layout.addView(img,params);
//        }
//
//        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//                int pos=i% pages.size();
//                for (int j = 0; j < pages.size(); j++) {
//                    ImageView img= (ImageView) layout.getChildAt(j);
//                    if(j==pos){
//                        img.setImageResource(R.drawable.page_on);
//                    }
//                    else{
//                        img.setImageResource(R.drawable.page_off);
//                    }
//                }
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//    }
//}
//class MyNewPagerAdapter extends PagerAdapter {
//    private List<View> pages;
//
//    public MyNewPagerAdapter(List<View> pages) {
//        this.pages = pages;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        int pos=position%pages.size();
//        container.addView(pages.get(pos));
//        return pages.get(pos);
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
////        super.destroyItem(container, position, object);
//        int pos=position%pages.size();
//        container.removeView(pages.get(pos));
//    }
//
//    @Override
//    public int getCount() {
//        return Integer.MAX_VALUE;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//        return view==o;
//    }
//}
//
