import org.opencv.core.Mat
import org.opencv.core.MatOfRect
import org.opencv.core.Rect
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import org.opencv.objdetect.Objdetect

const val CLASSIFIER_FILE_NAME = "haarcascade_frontalface_default.xml"

class FaceDetector {
    private var faceCascade: CascadeClassifier? = CascadeClassifier()

    init {
        faceCascade?.load(FaceDetector::class.java.classLoader?.getResource(CLASSIFIER_FILE_NAME)?.path)
    }

    fun detect(src: Mat): Array<Rect>{
        val faces = MatOfRect()
        val absoluteFaceSize = 20.0

        //Convert Mat to GrayScale
        val gray = Mat()
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGB2GRAY)

        // equalize the photo histogram to improve the result
        Imgproc.equalizeHist(gray, gray)

        faceCascade?.detectMultiScale(gray, faces, 1.3, 3,
            Objdetect.CASCADE_FIND_BIGGEST_OBJECT, Size(absoluteFaceSize, absoluteFaceSize)
        )

        val facesArray = faces.toArray()
        faces.release()
        gray.release()

        return facesArray
    }
}