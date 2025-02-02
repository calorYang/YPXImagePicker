## 关于YPXImagePicker

YPXImagePicker内部集成了微信的图片选择器、小红书样式的多图剪裁选择器、图片和视频预览、图片自定义比例剪裁等功能。

架构上采用序列化接口方式实现模块化解耦，app通过实现Presenter(指定选择器的交互逻辑、UI定制、图片加载框架等)接口方法从而与module达到框架层解耦。使用上采用嵌入fragment方式替换传统onActivityResult回调，支持跨进程回调，从而实现业务层代码解耦。另外，YPXImagePicker具有很强大的可定制性，内部提供非常多的配置项来满足用户各种各样的需求，微信、小红书、知乎等不同的UI风格都可以轻松实现。内置大图、长图、Gif图等预览功能，采用分段式加载，拒绝放大失真，同时还提供了强大但是超轻量级(仅一个CropImageView类)的图片多比例剪裁功能。

YPXImagePicker内部无so库、无任何第三方依赖(v7、v4除外)，使用者无需担心apk体积增大或者library冲突。本库现已投入多个商业项目的使用，稳定可靠，持续迭代！


apk下载:

![demo](https://www.pgyer.com/app/qrcode/Wfhb)

## 引用方式
 
- androidx版本： [ ![Download](https://api.bintray.com/packages/yangpeixing/ypxImagePicker/imagepicker_androidx/images/download.svg) ](https://bintray.com/yangpeixing/ypxImagePicker/imagepicker_androidx/_latestVersion)

	```java
	implementation 'com.ypx.imagepicker:ypxImagePicker:2.3.1'
	```
- support版本：[ ![Download](https://api.bintray.com/packages/yangpeixing/ypxImagePicker/imagepicker_support/images/download.svg) ](https://bintray.com/yangpeixing/ypxImagePicker/imagepicker_support/_latestVersion)

	```java
	implementation 'com.ypx.imagepicker:imagepicker_support:2.3.1'
 	```


## 效果图集
 - de'mo效果
 
![demo](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hcHAtc2NyZWVuc2hvdC5wZ3llci5jb20vaW1hZ2Uvdmlldy9hcHBfc2NyZWVuc2hvdHMvZWE4MjEzOTQzZTliNWJiY2NiY2E3NTIzNTZmZjlkMTYtNTI4) 

 - 微信样式
 
![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hcHAtc2NyZWVuc2hvdC5wZ3llci5jb20vaW1hZ2Uvdmlldy9hcHBfc2NyZWVuc2hvdHMvYjc3MjUwMmU3YjBhOTk1ZTJhZGY4NjFkZTg1YjhiMzAtNTI4)
![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hcHAtc2NyZWVuc2hvdC5wZ3llci5jb20vaW1hZ2Uvdmlldy9hcHBfc2NyZWVuc2hvdHMvMDQ4ZjY2MjI3YTQxYTUzYWRlNWM5ZmZhZWRkYzc1MGMtNTI4) 

 - 自定义样式
 
 ![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hcHAtc2NyZWVuc2hvdC5wZ3llci5jb20vaW1hZ2Uvdmlldy9hcHBfc2NyZWVuc2hvdHMvODZmMmNkNDQ4MDIzMzFmODg3MWQ5ODFiNmU0NDQ1NjAtNTI4)
 ![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hcHAtc2NyZWVuc2hvdC5wZ3llci5jb20vaW1hZ2Uvdmlldy9hcHBfc2NyZWVuc2hvdHMvY2M5MzVmNWVkZTM1NmJlYjkyOTIwYjJmZjczZWRjNjgtNTI4) 
 
 - 小红书样式

![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hcHAtc2NyZWVuc2hvdC5wZ3llci5jb20vaW1hZ2Uvdmlldy9hcHBfc2NyZWVuc2hvdHMvZDUzODc0N2VlZTA2ZDVjNzFiMzgwNTEyMTg0ZTczNTMtNTI4)

 - 自定义比例剪裁
 
 ![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hcHAtc2NyZWVuc2hvdC5wZ3llci5jb20vaW1hZ2Uvdmlldy9hcHBfc2NyZWVuc2hvdHMvMTRjZDJiZjIxMzk1MjVhMDhmZWZhNjdjNmExMjkwMWMtNTI4)

## 微信图片选择
支持视频、GIF、长图选择，支持单张多比例剪裁，支持多图预览、编辑、以及调序，支持直接拍照          

 - **多图/单图选择—— 支持视频和图片单一选择模式**
```java
//微信样式多选，WXImgPickerPresenter为用户自定义的微信显示样式，                                  
// 以及一些交互逻辑，实现自IMultiPickerBindPresenter接口                                   
ImagePicker.withMulti(new WXImgPickerPresenter())                            
        .setMaxCount(9)//设置最大选择数量                                            
        .setColumnCount(4)//设置显示列数                                           
        .showVideo(true)//设置是否加载视频                                           
        .showGif(true)//设置是否加载GIF                                            
        .showCamera(true)//设置是否显示拍照按钮（在列表第一个）          
        .showImage(true)//设置是否加载图片
        .setMaxVideoDuration(120000)//设置视频可选择的最大时长
        //设置只能选择视频或图片
        .setSinglePickImageOrVideoType(true)
        //设置只能选择一个视频 
        .setVideoSinglePick(true)               
        //设置下次选择需要屏蔽的图片或视频（简单点就是不可重复选择）                                      
        .setShieldList(new ArrayList<String>())                              
        //设置下次选择需要带入的图片和视频（简单点就是记录上次选择的图片，可以取消之前选择）                          
        .setLastImageList(new ArrayList<String>())                                                             
        //调用多选                                                               
        .pick(this, new OnImagePickCompleteListener() {                      
            @Override                                                        
            public void onImagePickComplete(ArrayList<ImageItem> items) {    
                //处理回调回来的图片信息，主线程                                            
            }                                                                
        });     
        
//Fragment调用：    
MultiImagePickerFragment fragment = ImagePicker.withMultiFragment(new WXImgPickerPresenter())
				.setMaxCount(9)//设置最大选择数量      
              	...//省略以上若干属性
                .pickWithFragment();
fragment.setOnImagePickCompleteListener(new OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {
		    }
                });
                                                         
```
 - **单张剪裁 —— 支持自定义剪裁比例**
```java
//微信样式多选，WXImgPickerPresenter为用户自定义的微信显示样式，                                  
// 以及一些交互逻辑，实现自IMultiPickerBindPresenter接口                                   
ImagePicker.withMulti(new WXImgPickerPresenter())                            
       	...//省略以上所有公共属性                                              
        .setCropRatio(1, 1)//设置剪裁比例   1：1  
	.cropSaveFilePath("剪裁图片保存地址")
        .cropRectMinMargin(dp(50))//设置剪裁边框间距
        //调用剪裁                                                              
        .crop(this, new OnImagePickCompleteListener() {                      
            @Override                                                        
            public void onImagePickComplete(ArrayList<ImageItem> items) {    
                //处理回调回来的图片信息，主线程                                            
            }                                                                
        });                                                                  
```
 - **预览 —— 支持普通预览和预览编辑（调序、删除）**
```java
  //预览数据源，只接受ArrayList<String> 和ArrayList<ImageItem> 两种泛型                          
ArrayList<String> imageList = new ArrayList<>();                                 
//默认选择的index                                                                     
int currentPos = 1;                                                              
//调用预览                                                                           
ImagePicker.withMulti(new WXImgPickerPresenter())                                
        //第二个参数为预览图片数组、第三个参数为默认选中的index，第四个参数为预览回调，                              
        //如果第四个参数为null,则代表无需对预览的图片进行编辑（调序、删除操作），反之可以编辑预览图                        
        .preview(this, imageList, currentPos, new OnImagePickCompleteListener() {
            @Override                                                            
            public void onImagePickComplete(ArrayList<ImageItem> items) {        
                //处理预览回调的数据                                                      
            }                                                                    
        });                                                                      
```

 - **拍照**
```java
  //直接调用拍照                                                                                             
ImagePicker.withMulti(new WXImgPickerPresenter()).takePhoto(this, new OnImagePickCompleteListener() {
    @Override                                                                                        
    public void onImagePickComplete(ArrayList<ImageItem> imageItems) {                               
        //处理拍照回调                                                                                                                        }                                                                                                
});                                                                                                  
```
 
 - **自定义样式 —— 支持图片文件夹列表弹入方向、支持图片item自定义 **
```java
/**
 * 作者：yangpeixing on 2018/9/26 15:57
 * 功能：微信样式图片选择器
 */
public class WXImgPickerPresenter implements IMultiPickerBindPresenter {                                       
                                                                                                               
    @Override                                                                                                  
    public void displayListImage(ImageView imageView, ImageItem item, int size) {                              
        Glide.with(imageView.getContext()).load(item.path).into(imageView);                                    
    }                                                                                                          
                                                                                                               
    @Override                                                                                                  
    public void displayPerViewImage(ImageView imageView, String url) {                                         
        Glide.with(imageView.getContext()).load(url).into(imageView);                                          
    }                                                                                                          
                                                                                                               
    @Override                                                                                                  
    public PickerUiConfig getUiConfig(Context context) {                                                       
        PickerUiConfig config = new PickerUiConfig();                                                          
        //是否沉浸式状态栏，状态栏颜色将根据TopBarBackgroundColor指定，                                                            
        // 并动态更改状态栏图标颜色                                                                                        
        config.setImmersionBar(true);                                                                          
        //设置主题色                                                                                                
        config.setThemeColor(Color.parseColor("#09C768"));                                                     
        //设置选中和未选中时图标                                                                                          
        config.setSelectedIconID(R.mipmap.picker_wechat_select);                                               
        config.setUnSelectIconID(R.mipmap.picker_wechat_unselect);                                             
        //设置返回图标以及返回图标颜色                                                                                       
        config.setBackIconID(R.mipmap.picker_icon_back_black);                                                 
        config.setBackIconColor(Color.BLACK);                                                                  
        //设置标题栏背景色和对齐方式，设置标题栏文本颜色                                                                              
        config.setTitleBarBackgroundColor(Color.parseColor("#F1F1F1"));                                        
        config.setTitleBarGravity(Gravity.START);                                                              
        config.setTitleColor(Color.BLACK);                                                                     
        //设置标题栏右上角完成按钮选中和未选中样式，以及文字颜色                                                                          
        int r = ViewSizeUtils.dp(context, 2);                                                                  
        config.setOkBtnSelectBackground(CornerUtils.cornerDrawable(Color.parseColor("#09C768"), r));           
        config.setOkBtnUnSelectBackground(CornerUtils.cornerDrawable(Color.parseColor("#B4ECCE"), r));         
        config.setOkBtnSelectTextColor(Color.WHITE);                                                           
        config.setOkBtnUnSelectTextColor(Color.parseColor("#50ffffff"));                                       
        config.setOkBtnText("完成");                                                                             
        //设置选择器背景色                                                                                             
        config.setPickerBackgroundColor(Color.WHITE);                                                          
        //设置选择器item背景色                                                                                         
        config.setPickerItemBackgroundColor(Color.parseColor("#484848"));                                      
        //设置底部栏颜色                                                                                              
        config.setBottomBarBackgroundColor(Color.parseColor("#333333"));                                       
        //设置拍照按钮图标和背景色                                                                                         
        config.setCameraIconID(R.mipmap.picker_ic_camera);                                                     
        config.setCameraBackgroundColor(Color.parseColor("#484848"));  
        
       	//标题栏模式，从标题栏选择相册
        config.setPickStyle(PickerUiConfig.PICK_STYLE_TITLE); 
        //设置选择器自定义item样式
        config.setPickerItemView(new CustomPickerItem(context));                                       
        return config;                                                                                         
    }                                                                                                          
                                                                                                               
    @Override                                                                                                  
    public void tip(Context context, String msg) {                                                             
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();                                               
    }                                                                                                          
                                                                                                               
    @Override                                                                                                  
    public void imageItemClick(Context context, ImageItem imageItem, ArrayList<ImageItem> selectImageList,     
                               ArrayList<ImageItem> allSetImageList, MultiGridAdapter adapter) {               
        tip(context, "我是自定义的图片点击事件");                                                                          
    }                                                                                                          
}                                                                                                                                                                                                                                                                                                                                       
```
 
## 小红书图片剪裁选择      
高仿小红书图片剪裁框架，支持视频以及多图剪裁、支持fragment样式侵入

 - **Activity直接调用**
```java
//调用小红书剪裁回调的imageItems里，imageItem.path是原图，                                    
// imageItem.getCropUrl()才是剪裁后的图片                                             
ImagePicker.withCrop(new RedBookCropPresenter())                              
        //设置第一张图信息，可为null,设置以后，选择器会默认                                         
        // 以第一张图片的剪裁方式剪裁后面所有的图片                                               
        .setFirstImageItem(new ImageItem())                                   
        .setFirstImageUrl("这里填入外部已经选择的第一张图片地址url")                            
        //设置要选择的最大数                                                           
        .setMaxCount(count)                                                   
        //设置是否显示底部自定义View                                                     
        .showBottomView(true)                                                 
        //设置是否加载视频                                                            
        .showVideo(true)                                                      
        //设置第一个item是否拍照                                                       
        .showCamera(true)                                                     
        //设置剪裁完图片保存路径                                                         
        .setCropPicSaveFilePath("图片保存路径")                                     
        .pick(this, new OnImagePickCompleteListener() {                       
            @Override                                                         
            public void onImagePickComplete(ArrayList<ImageItem> imageItems) {
                //调用小红书剪裁回调的imageItems里，imageItem.path是原图，                    
                // imageItem.getCropUrl()才是剪裁后的图片                             
                //TODO剪裁回调                                                    
            }                                                                 
        });                                                                                                                                             
```
 - **Fragment嵌套调用**

```java
//调用小红书剪裁回调的imageItems里，imageItem.path是原图，                                                  
// imageItem.getCropUrl()才是剪裁后的图片                                                           
ImagePickAndCropFragment fragment = ImagePicker.withCropFragment(new RedBookCropPresenter())
        //设置第一张图信息，可为null,设置以后，选择器会默认                                                       
        // 以第一张图片的剪裁方式剪裁后面所有的图片                                                             
        .setFirstImageItem(new ImageItem())                                                 
        .setFirstImageUrl("这里填入外部已经选择的第一张图片地址url")                                          
        //设置要选择的最大数                                                                         
        .setMaxCount(count)                                                                 
        //设置是否显示底部自定义View                                                                   
        .showBottomView(true)                                                               
        //设置是否加载视频                                                                          
        .showVideo(true)                                                                    
        //设置第一个item是否拍照                                                                     
        .showCamera(true)                                                                   
        //设置剪裁完图片保存路径                                                                       
        .setCropPicSaveFilePath("图片保存路径")                                                   
        .pickWithFragment();                                                                
fragment.setImageListener(new OnImagePickCompleteListener() {                               
    @Override                                                                               
    public void onImagePickComplete(ArrayList<ImageItem> items) {                           
        //TODO 图片剪裁完回调                                                                      
    }                                                                                       
});                                                                                         
```
外部activity需要重写的方法
```java
@Override                                                                                   
public void onBackPressed() {                                                               
    if (null != mFragment && mFragment.onBackPressed()) {                                   
        return;                                                                             
    }                                                                                       
    super.onBackPressed();                                                                  
}                                                                                           
                                                                                            
@Override                                                                                   
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {   
    super.onActivityResult(requestCode, resultCode, data);                                  
    if (mFragment != null) {                                                                
        mFragment.onTakePhotoResult(requestCode, resultCode);                               
    }                                                                                       
}                                                                                           
```

 - **自定义数据绑定交互**
```java
/**
 - Description: 小红书样式框架数据绑定
 - <p>
 - Author: peixing.yang
 - Date: 2019/2/21
 */
public class RedBookCropPresenter implements ICropPickerBindPresenter {
    //图片加载
    @Override
    public void displayListImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @Override
    public void displayCropImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    //自定义底部栏
    @Override
    public View getBottomView(final Context context) {
        TextView textView = new TextView(context);
        textView.setText("这是底部自定义View");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.RED);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewSizeUtils.dp(context, 50)));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        return textView;
    }

    //自定义草稿箱对话框，可在进入时直接弹出是否加载草稿
    @Override
    public void showDraftDialog(Context context) {

    }

    //视频点击
    @Override
    public void clickVideo(Context context, ImageItem imageItem) {
        Toast.makeText(context, imageItem.path, Toast.LENGTH_SHORT).show();
    }
} 
```
## 相关问题

 - **小红书剪裁框架暂且不支持UI自定义，暂不支持视频直接预览，2.4版本会迭代(预计2019.10.1前)**
 
 - **微信选择框架暂不支持图片高级编辑，2.5版本会加入(预计2019年内)**
 
 - **剪裁暂不支持图片旋转，可能会迭代，看需求**


本库来源于mars App,想要体验城市最新的吃喝玩乐，欢迎读者下载体验mars!



开发者：[yangpeixing](https://blog.csdn.net/qq_16674697)
email:313930500@qq.com

