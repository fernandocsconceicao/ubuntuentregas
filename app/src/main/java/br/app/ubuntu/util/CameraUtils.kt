package br.com.ubuntu.util
//
//import android.util.Log
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//
//object CameraUtils {
//    private fun capturarImagem() {
//        // Registers a photo picker activity launcher in single-select mode.
//        val pickMedia =
//            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//                // Callback is invoked after the user selects a media item or closes the
//                // photo picker.
//                if (uri != null) {
//                    Log.d("PhotoPicker", "Selected URI: $uri")
//                } else {
//                    Log.d("PhotoPicker", "No media selected")
//                }
//            }
//
//// Include only one of the following calls to launch(), depending on the types
//// of media that you want to let the user choose from.
//
//// Launch the photo picker and let the user choose images and videos.
////        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
//
////// Launch the photo picker and let the user choose only images.
//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
////
////// Launch the photo picker and let the user choose only videos.
////        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
//
//// Launch the photo picker and let the user choose only images/videos of a
//// specific MIME type, such as GIFs.
////        val mimeType = "image/gif"
////        pickMedia.launch(
////            PickVisualMediaRequest(
////                ActivityResultContracts.PickVisualMedia.SingleMimeType(
////                    mimeType
////                )
////            )
////        )
//    }
//}