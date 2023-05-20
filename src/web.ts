import type { CapOcrPlugin } from './definitions';
import { WebPlugin } from '@capacitor/core';
import Tesseract from "tesseract.js";

export class CapOcrWeb extends WebPlugin implements CapOcrPlugin {
  /**
   * Asynchronously detects data from an image using Tesseract OCR.
   * @param {Object} options - Options for detecting data.
   * @param {string} options.imageBase64 - The base64-encoded image data.
   * @returns {Promise<Object>} A Promise that resolves to an object with a `value` property containing the extracted text.
   * @throws {Error} Throws an error if the input data is invalid.
   */
  async detectText(options: { imageBase64: string}): Promise<{ value: string }> {
    const blob = this.getBlob(options.imageBase64);

    // recognise text via tesseract
    const result = await Tesseract.recognize(blob);
    return { value: result.data.text };
  }

  /**
   * Asynchronously detects data from an image using Tesseract OCR.
   * @param {Object} options - Options for detecting data.
   * @param {string} options.imageBase64 - The base64-encoded image data.
   * @returns {Promise<Object>} A promise that resolves to an object containing the detected data as a JSON string.
   * @throws {Error} Throws an error if the input data is invalid.
   */
  async detectData(options: { imageBase64: string }): Promise<{ value: string }> {
    const blob = this.getBlob(options.imageBase64);

    // recognise text via tesseract
    const result = await Tesseract.recognize(blob);
    return { value: JSON.stringify(result.data) };
  }

  /**
   * Converts a base64-encoded string to a Blob object.
   * @param {string} base64 - The base64-encoded string.
   * @returns {Blob} The Blob object representing the decoded data.
   * @throws {Error} Throws an error if the input data is null or undefined.
   */
  getBlob(base64: string): Blob {
    // checking the input we get if it is not null or undefined
    if (!base64) {
      throw new Error('Invalid input data');
    }

    // prepare data for tesseract
    const decodedData = new Uint8Array(atob(base64).split('').map(char => char.charCodeAt(0)));
    return new Blob([decodedData], { type: 'image/jpeg' });
  }
}
