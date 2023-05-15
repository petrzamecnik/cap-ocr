import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapOcrPlugin)
public class CapOcrPlugin: CAPPlugin {
    private let implementation = CapOcr()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }

    
    @objc func detectText(_ call: CAPPluginCall) {
        let value = call.getString("imageBase64") ?? ""
        call.resolve([
            "value": implementation.detectText(value)
        ])
    }
}
