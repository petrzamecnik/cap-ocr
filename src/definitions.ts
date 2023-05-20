export interface CapOcrPlugin {
  detectText(options: { imageBase64: string}): Promise<{value: string}>;
  detectData(options: { imageBase64: string }): Promise<{ value: string }>;
}
