package com.example.newyearcard.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.View;
import android.widget.Button;



import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

	
    private IWXAPI api;
@Override
    protected void onCreate(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    api = WXAPIFactory.createWXAPI(this, "wxc63a53549d0aba89", false);
	    api.handleIntent(getIntent(), this);
	    super.onCreate(savedInstanceState);
    }
	@Override
	public void onReq(BaseReq arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResp(BaseResp resp) {
		// TODO Auto-generated method stub
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			break;
		
		}
	}
   
    
}
