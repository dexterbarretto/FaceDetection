import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.WindowConstants

fun Mat.show(title:String = "Mat Display") {
    if (!this.empty()) {
        val matOfByte = MatOfByte()
        Imgcodecs.imencode(".png", this, matOfByte)
        val byteArray = matOfByte.toArray()
        var bufImage: BufferedImage?
        try {
            val inputStream: InputStream = ByteArrayInputStream(byteArray)
            bufImage = ImageIO.read(inputStream)
            val frame = JFrame()
            frame.title = title
            frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
            frame.contentPane.add(JLabel(ImageIcon(bufImage)))
            frame.pack()
            frame.isVisible = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    } else {
        println("Empty Mat found! Doing Nothing!")
    }
}