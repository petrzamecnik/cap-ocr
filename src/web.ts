import { WebPlugin } from '@capacitor/core';

import type { CapOcrPlugin } from './definitions';

export class CapOcrWeb extends WebPlugin implements CapOcrPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
