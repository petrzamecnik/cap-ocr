import Foundation
import Vision
import UIKit

@objc public class CapOcr: NSObject {
    var recognizedText: String?
    
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
    
    @objc public func detectText(_ imageBase64: String) -> String {
        
        guard let imageData = Data(base64Encoded: imageBase64) else {
            return "Unable to convert base64 string to data."
        }
        
        guard let uiImage = UIImage(data: imageData) else {
            return "Unable to create UIImage from data."
        }
        
        guard let cgImage = uiImage.cgImage else {
            return "Unable to get CGImage from UIImage."
        }
        
        let requestHandler = VNImageRequestHandler(cgImage: cgImage)
        let request = VNRecognizeTextRequest(completionHandler: recognizeTextHandler)
        
        do {
            try requestHandler.perform([request])
        } catch {
            return "Unable to perform the requests..."
        }
         
        print(recognizedText)
        return recognizedText ?? ""
    }
    

    func recognizeTextHandler(request: VNRequest, error: Error?) {
            guard let observations =
                    request.results as? [VNRecognizedTextObservation] else {
                return
            }
            
            // Store the recognized text in the `recognizedText` property.
            let recognizedStrings = observations.compactMap { observation in
                // Return the string of the top VNRecognizedText instance.
                return observation.topCandidates(1).first?.string
            }
            recognizedText = recognizedStrings.joined(separator: "\n")
        }
   
    
}
