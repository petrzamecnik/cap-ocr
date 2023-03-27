import { registerPlugin } from '@capacitor/core';

import type { CapOcrPlugin } from './definitions';

const CapOcr = registerPlugin<CapOcrPlugin>('CapOcr', {
  web: () => import('./web').then(m => new m.CapOcrWeb()),
});

export * from './definitions';
export { CapOcr };
