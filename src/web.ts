import type { CapOcrPlugin } from './definitions';
import { WebPlugin } from '@capacitor/core';
import Tesseract from "tesseract.js";

export class CapOcrWeb extends WebPlugin implements CapOcrPlugin {
  /**
   * Extracts text from an image encoded in base64 format.
   *
   * @async
   * @function detectText
   * @memberof CapOcrPlugin
   *
   * @param {string} options.imageBase64 - The base64-encoded image data.
   *
   * @returns {Promise<Object>} A Promise that resolves to an object with a `value` property containing the extracted text.
   *
   * @throws {Error} An error is thrown if the input data is null, undefined, or an empty string.
   * @param options.imageBase64
   */
  async detectText(options: { imageBase64: string}): Promise<{ value: string }> {
    // checking the input we get if it is not null or undefined
    if (!options.imageBase64) {
      throw new Error('Invalid input data');
    }

    // prepare data for tesseract
    const decodedData = new Uint8Array(atob(options.imageBase64).split('').map(char => char.charCodeAt(0)));
    const blob = new Blob([decodedData], { type: 'image/jpeg' });

    // recognise text via tesseract
    const result = await Tesseract.recognize(blob);
    return { value: result.data.text };
  }
}
