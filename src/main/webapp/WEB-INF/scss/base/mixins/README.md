# Base/Mixins

## Table of Contents 
- [SCSS/README.md](../../README.md#ost-ui-framework-css/scss)<br><br>
- [contrastBgText](#contrastbgtext)
- [transitionMulti](#transitionmulti)
- [textDot](#textdot)
- [fonts](#fonts)
  - [fontSmoot](#fontsmoot)
  - [textStyle](#textstyle)
  - [iconStyle](#iconstyle)
- [scrollbar](#scrollbar)

## contrastBgText
  คือ Mixins ที่ไว้กำหนดค่าสีพื้นหลังและตัวอักษรให้เป็นสีตรงข้ามกัน หากกำหนดค่าพื้นหลังเป็นสีเข้มค่าตัวอักษรที่ได้จะเป็นที่ขาว(หรือค่าตัวแปร `$textLight` ใน `@function contrastColor`) แต่ถ้ากำหนดค่าพื้นหลังเป็นสีอ่อนค่าตัวอักษรที่ได้จะเป็นที่ดำ(หรือค่าตัวแปร `$textDark` ใน `@function contrastColor`) 
  - `$number` คือค่าที่เราต้องการตัดหน่วยออก
  
  ```scss
  @mixin contrastBgText($bgColor);
  
  // use function
  div{
    @include contrastBgText(#000);
  }

  // compiled to css
  div{
    color: #fff;
    background: #000;
  }
  ```

## transitionMulti
  คือ Mixins ที่ไว้กำหนดรูปแบบของ `transition` ประกอบด้วย property
  - `$property:()` คือค่า property ของ css ที่ต้องการให้เกิด transition โดยมีค่า default เท่ากับ `all`
  - `$duration` คือค่าระยะเวลาในการทำงาน transition รับค่า `time|initial|inherit;` โดยมีค่า default เท่ากับ `.3s` ในตัวแปร `$transitionMulti-variables`
  - `$timing` หรือ `timing-function` คือรูปแบบของการทำงาน transition รับค่า `linear|ease|ease-in|ease-out|ease-in-out|step-start|step-end|steps(int,start|end)|cubic-bezier(n,n,n,n)|initial|inherit` โดยมีค่า default เท่ากับ `linear` ในตัวแปร `$transitionMulti-variables`
  - `$delay` คือค่าระยะเวลาก่อนเกิด transition รับค่า `time|initial|inherit;` โดยมีค่า default เท่ากับ `null` ในตัวแปร `$transitionMulti-variables` 
  
  ```scss

  $transitionMulti-variables:(
  'delay'    : 0,
  'duration' : .3s,
  'timing'   : linear
  )!default;

  @mixin transitionMulti($property:(), $duration, $timing, $delay);
  
  // use function
  // --> กรณีไม่กำหนดค่าอะไร
  .case1{
    @include transitionMulti();
  }
  // --> กรณีกำหนดค่า property แล้วให้ duration, timing, delay เป็นค่าเดียวกันหมด
  .case2{
    @include transitionMulti((height,width)) ;
  }
  // --> กรณีมีชุด transition บางตัวที่ไม่เหมือนค่าในตัวแปร $transitionMulti-variables
  .case3{
    @include transitionMulti(((left 1s ease-in-out),height,width)) ;
  }

  // compiled to css
  // --> กรณีไม่กำหนดค่าอะไร
  .case1{
    transition: all .3s linear;
  }
  // --> กรณีกำหนดค่า property แล้วให้ duration, timing, delay เป็นค่าเดียวกันหมด
  .case2{
    transition-property: height, width;
    transition-duration: .3s;
    transition-timing: linear;
  }
  // --> กรณีมีชุด transition บางตัวที่ไม่เหมือนค่าในตัวแปร $transitionMulti-variables
  .case3{
    transition: left 1s ease-in-out, height .3s linear, width .3s linear;
  }
  ```

  ## textDot
  คือ Mixins ที่ไว้ตัดคำแถวเติม `...` ต่อท้าย ในกรณีข้อความบรรทัดเดียว ประกอบด้วย property
  - `width` คือความกว้างที่จะให้เริ่มตัดคำ รับค่า `number+unit` มีค่า default เท่ากับ `100%`
  - `clear` ไว้สำหรับการ clear ค่า element ที่เรียกใช้ `textDot` ให้เป็นค่า `initial` รับค่า `true|false` มีค่า default เท่ากับ `false` 
  
  ```scss
  @mixin textDot($property: ());
  
  // use function
  .case1{
    @include textDot(('width': 300px));
  }
  .case2{
    @include textDot(('clear': true));
  }
  .case2{
    @include textDot(('clear': true,'width': 300px));
  }

  // compiled to css
  .case1{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 300px;
  }
  .case2{
    overflow: initial;
    text-overflow: initial;
    white-space: initial;
    width: initial;
  }
  .case3{
    overflow: initial;
    text-overflow: initial;
    white-space: initial;
    width: 300px;
  }
  ```

## fonts
### `fontSmoot`
  คือ Mixins ที่ไว้กำหนดค่ารูปแบบ text หรือ icon ให้เกิดความ Smoot
  ```scss
  @mixin fontSmoot();
  
  // use function
  div{
    @include fontSmoot();
  }

  // compiled to css
  div{
    speak: none;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
  ```

### `textStyle`
  คือ Mixins ที่ไว้กำหนดค่ารูปแบบ text ประกอบด้วย property
  -  `color` คือค่าสีที่ต้องการให้มีผลกับ text รับค่าเป็น `color code | color name` มีค่า default เท่ากับ `#000`
  -  `font-family` คือชื่อของ font ที่เรียกใช้งาน รับค่าเป็น `font name` มีค่า default เท่ากับ `sans-serif`
  -  `fontSmoot` คือค่าที่กำหนดความ Smoot รับค่าเป็น `true | false` มีค่า default เท่ากับ `false`

  ```scss
  @mixin textStyle($property : ());
  
  // use function
  div{
    @include textStyle();
  }

  // compiled to css
  div{
    color: #000;
    font-family: sans-serif;
    font-style: normal;
    font-variant: normal;
    font-weight: normal;
    text-transform: none;
  }
  ```

### `iconStyle`
  คือ Mixins ที่ไว้กำหนดค่ารูปแบบ icon ประกอบด้วย property
  -  `color` คือค่าสีที่ต้องการให้มีผลกับ text รับค่าเป็น `color code | color name` มีค่า default เท่ากับ `#000`
  -  `font-family` คือชื่อของ font ที่เรียกใช้งาน รับค่าเป็น `font name` มีค่า default เท่ากับ `fontIconName`
  -  `fontSmoot` คือค่าที่กำหนดความ Smoot รับค่าเป็น `true | false` มีค่า default เท่ากับ `true`
  ```scss
  @mixin iconStyle($property : ());
  
  // use function
  div{
    @include iconStyle();
  }

  // compiled to css
  div{
    color: #000;
    font-family: fontIconName;
    font-style: normal;
    font-variant: normal;
    font-weight: normal;
    text-transform: none;
    speak: none;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale
  }
  ```

## scrollbar
  คือ Mixins ที่ไว้กำหนดรูปแบบของ scrollbar ประกอบด้วย property
  - `$size` คือขนาดของตัว scrollbar รับค่า `number+unit` มีค่า default เท่ากับ `12px`
  - `$track:()` คือรูปแบบของพื้นหลัง scrollbar รับค่า `()` มีค่า default เท่ากับ
    - `'background-color'   : #f7f7f7`
    - `'border-radius'      : 0px,`
    - `'hover':('background-color' : #f4f4f4)`
  - `$thumb:()` คือรูปแบบของ scrollbar ที่ไว้จับลาก รับค่า `()` มีค่า default เท่ากับ
    - `'background-color'   : #e7e7e7`
    - `'border-radius'      : 0px,`
    - `'hover':('background-color' : #e0e0e0)`
  โดย `scrollbar()` หากเรียกใช้งานใน element ไหนก็จะมีผลกับ element

  ```scss
  @mixin scrollbar($size:$scrollbar-size, $track:(), $thumb:());
  
  // use function
  @include scrollbar();

  // compiled to css
  ::-webkit-scrollbar {
    height: 12px;
    width: 12px
  }

  ::-webkit-scrollbar-track {
    background-color: #f7f7f7;
    border-radius: 0
  }

  ::-webkit-scrollbar-track:hover {
    background-color: #f4f4f4
  }

  ::-webkit-scrollbar-thumb {
    background-color: #e7e7e7;
    border-radius: 0
  }

  ::-webkit-scrollbar-thumb:hover {
    background-color: #e0e0e0
  }
  ```