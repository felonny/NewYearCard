package com.example.newyearcard;


import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;



public class MainActivity extends Activity {

	private ImageView image;
	private EditText et;
	private IWXAPI iwxapi;
	private Button share;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iwxapi = WXAPIFactory.createWXAPI(this, "wxc63a53549d0aba89");
		iwxapi.registerApp("wxc63a53549d0aba89");
		et = (EditText) findViewById(R.id.word);
		image = (ImageView) findViewById(R.id.photo);
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,null);
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			    startActivityForResult(intent, 100);
			}
		});
		//给输入文字设定字体
		et.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/test.ttf"));
		share=(Button) findViewById(R.id.share);
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//image.setImageBitmap(generateNewYearCard());
				wecharShare();
				share.setVisibility(View.VISIBLE);
			}

			
		});
	}
	private void wecharShare() {
		// TODO Auto-generated method stub
		WXWebpageObject webpage = new WXWebpageObject();
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.mediaObject = new WXImageObject(generateNewYearCard());
		
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		//req.scene = SendMessageToWX.Req.WXSceneSession;
		req.scene = SendMessageToWX.Req.WXSceneTimeline;
		iwxapi.sendReq(req);
		
	}
	private Bitmap generateNewYearCard() {
		// TODO Auto-generated method stub
		//截图之前先把按钮影藏掉
		share.setVisibility(View.INVISIBLE);
		View view = getWindow().getDecorView();
		view .setDrawingCacheEnabled(true);
		//调取系统截图的功能
		view.buildDrawingCache();
		return view.getDrawingCache();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 100 && resultCode == RESULT_OK){
			if(data != null){
				image.setImageURI(data.getData());
			}
		}
	}


}
