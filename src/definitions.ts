export interface CapOcrPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
