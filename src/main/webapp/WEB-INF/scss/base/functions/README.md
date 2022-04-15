# Base/Functions

## Table of Contents 
- [SCSS/README.md](../../README.md#ost-ui-framework-css/scss)<br><br>
- [stripUnit](#stripunit)
- [calcUnit](#calcunit)
  - [`em `](#em)
  - [`rem`](#rem)
  - [`px`](#px)
- [contrastColor](#contrastcolor)
- [zIndex](#zindex)

## stripUnit
  คือ function ที่ไว้ตัดหน่วยต่างๆ ประกอบด้วย property
  - `$number` คือค่าที่เราต้องการตัดหน่วยออก
  
  ```scss
  css-property : stripUnit($number);
  
  // use function
  $number : stripUnit(8px); // $number: 8;
  width : 2px * $number; 

  // compiled to css
  width : 16px;
  ```

## calcUnit
### `em`

  คือ function ที่แปลงค่า px ให้เป็น em ประกอบด้วย property
  - `$pixel` คือค่าที่เราต้องการแปลงให้เป็นหน่วย em 
  - `$parentContext` คือค่า context ของ parent element โดยจะระบุค่า default เป็นค่าเดียวกับ `$browserContext--calc: 16!default` 

  ```scss
  css-property : em($pixel, $parentContext: $browserContext--calc);
  
  // use function
  width : em(16px);

  // compiled to css
  width : 1em;
  ```

### `rem`
  
  คือ function ที่แปลงค่า px ให้เป็น rem ประกอบด้วย property
  - `$pixel` คือค่าที่เราต้องการแปลงให้เป็นหน่วย em 
  - `$browserContext` คือค่า context ของ root html โดยจะระบุค่า default เท่ากับค่า `$browserContext--calc: 16!default`
  
  ```scss
  css-property : rem($pixel, $browserContext: $browserContext--calc);
  
  // use function
  width : rem(16px);

  // compiled to css
  width : 1rem;
  ```

### `px` 

  คือ function ที่แปลงค่า em หรือ rem ให้เป็น px ประกอบด้วย property 
  - `$pixel` คือค่าที่เราต้องการแปลงให้เป็นหน่วย em 
  - `$parentContext` คือค่า context ของ parent element โดยจะระบุค่า default เป็นค่าเดียวกับ `$browserContext--calc: 16!default` 
  - `$browserContext` คือค่า context ของ root html โดยจะระบุค่า default เท่ากับค่า `$browserContext--calc: 16!default`
  
  *** หากค่า context ของ parent element ไม่ใช่ค่าเดียวกับค่า context ของ root html จะต้องระบุค่า context ของ parent element ด้วยเสมอ
  
  ```scss
  css-property : rem($pixel, $browserContext: $browserContext--calc);
  
  // use function
  width : px(1em,20px);
  height : px(1rem);

  // compiled to css
  width : 20px
  height : 16px
  ```

## contrastColor

  คือ function ที่รับค่าสี และจะ return สีเข้มหรือสีอ่อนให้เหมาะสมกับสี parameter เช่น ส่งค่าสีเข้มเข้าไป function จะ return สีอ่อนกลับมา
  - `$color` คือค่าสีที่ต้องการ contrast
  
  ```scss
  css-property : contrastColor($color);

  // use function
  .btn {
    &.--light {
      color : contrastColor(#fff);
      background : #fff;
    }
    &.--dark {
      color : contrastColor(#000);
      background : #000;
    }
  }
  
  // compiled to css
  .btn.--light {
    color: #000;
    background: #fff;
  }

  .btn.--dark {
    color: #fff;
    background: #000;
  }
  ```

## zIndex

  คือ function ที่ return ค่า z-index จาก list และ nest list
  - list คือ กลุ่มของ nest list
  - nest list คือกลุ่มของ class ที่ต้องการค่า z-index ค่าเดียวกัน
  - ภายใน nest list จะประกอบด้วยชื่อ class และค่า z-index
  - item ตัวแรกใน nest list ให้กำหนดเป็นค่า z-index
  - `$key` คือชื่อ item ใน nest list โดยแนะนำให้กำหนด item ใน nest list เป็นชื่อของ class เพื่อความสะดวก

  ```scss
  z-index : zIndex($key);

  // use function
  $zIndexList: (
    (1, 'container', 'navbar'),
    (10, 'modal', 'modal-button'),
    (11, 'modal-tooltip')
  );

  .container {
    z-index : zIndex('container');
  }

  .navbar {
    z-index : zIndex('navbar');
  }

  .modal {
    z-index : zIndex('modal');
    &-button {
      z-index : zIndex('modal-button');
    }
    &-tooltip {
      z-index : zIndex('modal-tooltip');
    }
  }

  // compiled to css
  .container {
    z-index : 1;
  }

  .navbar {
    z-index : 1;
  }

  .modal {
    z-index : 10;
  }

  .modal-button {
    z-index : 10;
  }

  .modal-tooltip {
    z-index : 11;
  }
  ```