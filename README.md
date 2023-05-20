# cap-ocr

This plugins enables text recognition from images and camera.

## Install

```bash
npm install cap-ocr
npx cap sync
```

## API

<docgen-index>

* [`detectText(...)`](#detecttext)
* [`detectData(...)`](#detectdata)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### detectText(...)

```typescript
detectText(options: { imageBase64: string; }) => Promise<{ value: string; }>
```

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ imageBase64: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### detectData(...)

```typescript
detectData(options: { imageBase64: string; }) => Promise<{ value: string; }>
```

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ imageBase64: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------

</docgen-api>
