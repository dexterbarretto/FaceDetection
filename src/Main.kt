import org.opencv.core.Core
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgproc.Imgproc

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
            print("Loaded OpenCV version ${Core.VERSION}")

            val source = imread(Main::class.java.classLoader?.getResource("test.jpg")?.path,
                Imgcodecs.IMREAD_COLOR)
            val faceDetector = FaceDetector()
            val faceArray = faceDetector.detect(source)

            for (face in faceArray) {
                Imgproc.rectangle(source, face, Scalar(0.0, 0.0, 255.0))
            }

            source.show("Result")
        }
    }
}