package com.example.itstym.livecyptorate.Receive

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceHolder
import android.widget.Toast
import com.example.itstym.livecyptorate.HelperClass.PermissionUtils
import com.example.itstym.livecyptorate.R
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_receive.*
import kotlinx.android.synthetic.main.toolbar.*


class ReceiveCryptoCurrencyActivity() : AppCompatActivity(), PermissionUtils.PermissionResultCallback {
    override fun onStop() {
        super.onStop()
        stopCamera()
    }

    private fun stopCamera() {
        cameraSource?.release();
        cameraSource=null;
    }


    var mPermissionUtis:PermissionUtils?=null
    var permissions:ArrayList<String>?=null
    var isPermissionGranted:Boolean=false
    var holder:SurfaceHolder?=null
    var barcodeDetector: BarcodeDetector? = null
    var cameraSource: CameraSource? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)

        setSupportActionBar(toolbar)

        if (toolbar!=null){
            supportActionBar?.title = "RECEIVE"
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }

        //check camera permission
        mPermissionUtis= PermissionUtils(this@ReceiveCryptoCurrencyActivity)

        permissions= ArrayList()
        permissions!!.add(Manifest.permission.CAMERA)

        mPermissionUtis?.check_permission(permissions,"Need Camera permission to scan qr code",1)

        //set up camera
        cameraView.setZOrderMediaOverlay(true)
        holder=cameraView.holder


    }

    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()


    }


    override fun PermissionGranted(request_code: Int) {
        isPermissionGranted=true

        //intilize barcode reader
        barcodeDetector = BarcodeDetector.Builder(this@ReceiveCryptoCurrencyActivity)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()

        //initial camera parameters
        cameraSource = CameraSource.Builder(this, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24f)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920, 1024)
                .build()


        //check barcode detector
        if (!barcodeDetector!!.isOperational) {

            Toast.makeText(this, "Sorry Can't setup", Toast.LENGTH_LONG).show()

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
            val lowstorageFilter = IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW)
            val hasLowStorage = registerReceiver(null, lowstorageFilter) != null

            if (hasLowStorage) {
                Toast.makeText(this, "Low Storage", Toast.LENGTH_LONG).show()
            }

            this.finish()
        }

        holder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

                try {
                    cameraSource?.start(cameraView.holder)

                } catch (i: SecurityException) {
                    Log.e("cameraSource start()", "Yes")
                    i.printStackTrace()
                    Toast.makeText(this@ReceiveCryptoCurrencyActivity, "Error while opening the camera", Toast.LENGTH_LONG).show()
                }

            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {

                cameraSource?.stop()
            }
        })

        barcodeDetector?.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {

                val barcodes = detections.detectedItems

                if (barcodes.size() > 0) {
                    Log.i("Barcode",barcodes.valueAt(0).displayValue)
                    barcodeResult.text = barcodes.valueAt(0).displayValue

                }

            }
        })


    }

    override fun PartialPermissionGranted(request_code: Int, granted_permissions: java.util.ArrayList<String>?) {
        Log.i("PERMISSION PARTIALLY","GRANTED");

    }

    override fun PermissionDenied(request_code: Int) {
        Log.i("PERMISSION","DENIED");

        Toast.makeText(this@ReceiveCryptoCurrencyActivity,"Please add the permission to read QR code.",Toast.LENGTH_LONG).show()
    }

    override fun NeverAskAgain(request_code: Int) {
        Log.i("PERMISSION","NEVER ASK AGAIN");
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        // redirects to utils
        mPermissionUtis?.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

}
