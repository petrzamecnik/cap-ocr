export interface CapOcrPlugin {
  // echo(options: { value: string }): Promise<{ value: string }>;
  detectText(options: { imageBase64: string}): Promise<{value: string}>;
}
