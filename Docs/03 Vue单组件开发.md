# Vue 单组件开发

[toc]

## 组件

* **组件是可复用的 Vue 实例**

  * 组件名字更有意义
  * 可复用：只维护一套逻辑即可

* 通常一个应用会以一棵嵌套的组件树的形式来组织

  * 页头、侧边栏、内容区
  * 每个组件又可以包含子组件 

  ![](images\应用组件化.png)

* 入口组件：App.vue

## 组件的构成

* `<template>`：提供内容模板
  * HTML 语言
  * 模板语法
  * 只能有一个根元素。一般为`<div></div>`
* `<script>`：提供交互逻辑
  * JavaScript 语言
* `<style>`：提供样式
  * CSS 语言
  * scoped：只影响本组件的样式
* 三部分内容都可选

## 模板语法

### 纯 HTML 静态内容

```vue
<template>
  <div>
    Hello Vue!
  </div>
</template>
```

### 插值

* 文本

  * 简单文本
  * 此处 `message` 称为组件的属性（Property）
    * 组件的属性：`data` 方法返回对象中的属性
    * 注意要与 `HTML` 标签属性区别开来
      * 如`<div id="content"></div>`中的 `id` 属性

  ```vue
  <template>
    <span>Message: {{ message }}</span>
  </template>
  <script>
    export default {
      data() { return { message: 'hello' } }
    }
  </script>
  ```

  * JavaScript 表达式

  ```vue
  <template>
    <span>Number: {{ number + 1 }}</span>
    <span>Message: {{ ok ? 'YES' : 'NO' }}</span>
  </template>
  <script>
    export default {
      data() {
        return ({
          ok: true,
          number: 1,
        });
      }
    }
  </script>
  ```


#### 计算属性

* 用于放置复杂的模板计算逻辑

* 模板内的表达式非常便利，但设计它们的初衷是用于简单运算的
* 在模板中放入太多的逻辑会让模板过于笨重
  * 如 `{{ message.split('').reverse().join('') }}`
* 计算属性一般依赖于 `data` 定义的属性，并且只有在其依赖的属性发生变化时，才会更新 `DOM`

```vue
<template>
  <div>
    <p>原字符串："{{ message }}"</p>
    <p>翻转后字符串："{{ reversedMessage }}"</p>
  </div>
</template>
<script>
  export default {
    data() {
      return { message: 'Hello' }
    },
    computed: {
      reversedMessage() {
        // 注意 message 前面要加 this
        // 只有在 this.message 发生变化时才会重新计算
        return this.message.split('').reverse().join('');
      }
    }
  }
</script>
```

### 指令（Directives）

* 指令是带有 `v-` 前缀的特殊属性 (`attribute`)
* 除了 `v-for` 指令，指令的值应该为**单个 JavaScript 表达式**，如`v-if = 'seen'`
* 参数：指令可以接收一个“参数”，如 `v-bind:href='url'` 中的 `href` 称为指令 `v-bind` 的参数
* 修饰符：是以点 . 指明的特殊后缀，用于指出一个指令应以特殊方式绑定。如 `<form v-on:submit.prevent="onSubmit">...</form>` 中的 `.prevent` 修饰符对于触发的事件调用 `event.preventDefault()`

#### v-bind 指令

* 用于计算 html 属性值

  * 加上 v-bind 后，Vue 会把 `html` 属性的值当作 `javascript` 表达式对待
  * 如果没有加 `v-bind` ，html 属性的值只会被当作普通的字符串处理

  ```vue
  <template>
    <h2 id="message" v-bind:name="name">
      {{ message }}
    </h2>
  </template>
  <script>
    export default {
      data() {
        return ({
          name: 'greeting',
          message: '你好！'
        });
      }
    }
  </script>
  ```

* `v-bind:xxx` 可简写为：`:xxx`

#### v-if 指令

* 用于条件性地渲染一块内容

* 这块内容只有在指令的表达式为 `true` 时被渲染

* 如果指令表达式值为 `false`，则什么也不做

  ```vue
  <template>
    <div>
      <h1 v-if="false">永远不会被显示</h1>
      <h1 v-if="open">门已打开</h1>
      <h1 v-if="!open">门已关闭</h1>
      <button v-on:click="toggle">开关</button>
    </div>
  </template>
  <script>
    export default {
      data() {
        return { open: true }
      },
      methods: {
        toggle() { this.open = !this.open; }
      }
    }
  </script>
  ```

#### v-else 指令

```vue
<template>
  <div>
    <h1 v-if="false">永远不会被显示</h1>
    <h1 v-if="open">门已打开</h1>
    <h1 v-else>门已关闭</h1>
    <button v-on:click="toggle">开关</button>
  </div>
</template>
<script>
  export default {
    data() {
      return { open: true }
    },
    methods: {
      toggle() { this.open = !this.open; }
    }
  }
</script>
```

* 还有 `v-else-if` 指令：https://cn.vuejs.org/v2/guide/conditional.html#v-else-if

#### v-show 指令

* 与 `v-if ` 指令不同，不管`v-show` 指令表达式值是否为 `true` 都会对内容进行渲染
  * 如果为 `true`：显示内容
  * 如果为 `false`：隐藏内容
* `v-if` 有更大的切换开销，`v-show` 有更高的初始渲染开销
  * 如果需要频繁切换，用 `v-show`，否则用 `v-if`

#### v-for 指令

* 基于一个数组来渲染列表

* 使用 `item in items` 或 `item of items` 形式的语法

* 为每一项提供一个唯一 `key` 属性

  ```vue
  <template>
    <div>
      <h2>待办事项</h2>
      <ul>
        <li v-for="task in tasks" :key="task.id">
          {{ task.title }}
        </li>
      </ul>
    </div>
  </template>
  <script>
    export default {
      data() {
        return ({
          tasks: [
            {id: 1, title: '学习 Vue 指令'},
            {id: 2, title: '打一局游戏'},
            {id: 3, title: '操场上跑五圈'}
          ]
        });
      }
    }
  </script>
  ```

* 再来一例

  ```vue
  <template>
    <div>
      <h2>缺勤学生名单</h2>
      <ul>
        <li v-for="student of students" :key="student.name">
          {{ student.name }} 缺勤 {{ student.count }} 次
        </li>
      </ul>
    </div>
  </template>
  <script>
  export default {
    data() {
      return ({
        students: [
          {name: '张三', count: 5},
          {name: '李四', count: 3},
          {name: '王五', count: 0},
        ]
      });
    }
  }
  </script>
  ```

#### v-on 指令：事件处理

* 用于监听 DOM 事件，并在事件触发时执行一些 JavaScript 代码

  ```vue
  <template>
    <button v-on:click="counter += 1"> 加1 </button>
    <p>按钮被点击了 {{ counter }} 次</p>
  </template>
  <script>
    export default {
      data: { counter: 0 }
    }
  </script>
  ```

* 对于复杂的处理逻辑，可以把代码封装成方法

  ```vue
  <template>
    <!-- greet 是在下面定义的方法名 -->
    <button v-on:click="greet">Greet</button>
  </template>
  <script>
  export default {
    data: function () {
      return { name: 'Alice'};
    },
    methods: {
      greet: function(event) {
        alert(`Hello ${this.name}`);
        // event 是原生 DOM 事件
        if (event) {
          // 显示事件关联的 DOM 元素的标签名
          alert(event.target.tagName);
        }
      }
    }
  }
  </script>
  ```

* 除了直接绑定到一个方法，也可以在内联 JavaScript 语句中调用方法

  ```vue
  <template>
    <div>
      <button v-on:click="say('hi')" style="margin-right: 5px;">Say Hi</button>
      <button v-on:click="say('what')">Say what</button>
    </div>
  </template>
  <script>
    export default {
      methods: {
        say(message) {
          alert(message);
        }
      }
    }
  </script>
  ```

  ```vue
  <template>
    <button v-on:click="warn('不能提交表单', $event)">
      提交
    </button>
  </template>
  <script>
    export default {
      methods: {
        warn(message, event) {
          if (event) {
            event.preventDefault();
          }
          alert(message);
        }
      }
    }
  </script>
  ```

* `click` 事件是最常用的事件，其他事件还有

  * 按键事件，如回车键，F2键等
  * 其他鼠标事件：如 `mousein` , `mouseout` 等
  * 其他 DOM 事件，如输入框输入事件，选择事件等

* `v-on:xxx` 可以简写为 `@xxx`

#### v-model 

* 用于在表单`<input>, <textarea>, <select>` 元素上创建双向数据绑定

* 监听用户的输入事件并更新数据

* 内部为不同的输入元素使用不同的 `property` 并抛出不同的事件
  * text (`<input type="text">`) 和 textarea 元素使用 `value` 属性和 `input` 事件
  * checkbox (`<input type="checkbox">`) 和 radio (`<input type="checkbox">`) 元素使用 `checked` 属性和 `change` 事件
  * `select` 元素使用 `value` 属性和 `change` 事件
  
* 文本

  ```vue
  <template>
    <div>
      <input v-model="message" placeholder="edit me" />
      <p> Message is {{ message }} </p>
    </div>
  </template>
  <script>
    export default {
      data() {
        return { message: 'hello' };
      }
    }
  </script>
  ```

* 多行文本

  ```vue
  <template>
    <div>
      <input v-model="message" placeholder="添加多行文本" />
      <p> Message is {{ message }} </p>
    </div>
  </template>
  <script>
    export default {
      data() {
        return { message: 'hello' };
      }
    }
  </script>
  ```

* 选择框（单选）

  ```vue
  <template>
    <div>
      <select v-model="selected">
        <option disabled value="">请选择</option>
        <option>A</option>
        <option>B</option>
        <option>C</option>
      </select>
      <span>Selected: {{ selected }}</span>
    </div>
  </template>
  <script>
    export default {
      data() {
        return { selected: '' };
      }
    }
  </script>
  ```

## 组件的生命周期



## 综合应用

* 目标：综合所有的指令和属性相关语法，开发一个单组件应用
* 可能还会加入与插槽和生命周期有关的实现
* 会被进一步改造成多组件应用
* 课程后半部，会与基于 `Spring Boot` 开发的后端进行集成

### 功能描述

* 添加一条待办事项，长度**不超过 20 个字**
* 删除待办事项
* **即时修改**待办事项
* 标记已完成事项，已完成事项不能被修改
* 支持以下显示模式，以**创建时间倒序显示**
  * 每一条待办事项
  * 已完成的待办事项
  * 未完成的待办事项
* 将数据保存到 localStorage 中

## 实验一：单组件应用开发

* 实验目的
  * 理解 Vue 响应式开发思想
  * 掌握 Vue 组件的基础语法与构成
* 开发一个单组件记账应用，功能如下
  * 添加每笔收入或支出，主要信息包括
    * 收支类型：收入或支出
    * 描述：不超过 10 个字符的说明
    * 金额，保留两位小数，单位为元
  * 修改或删除每笔收入或支出
  * 收支明细支持四种显示方式，按日期倒序
    * 每笔收支、收入或支出
    * 按日、周、月显示（选做）
  * 在显示表格下方显示 总收入，总支出（选做）
  * 数据保存在 localStorage 中，可供长期使用（选做）
* 实验要求
  * 9 月 25 号之前提交作业
  * 实验报告和代码要一同提交到学习通
  * **不要打包 node_modules 目录**
  * 实验报告模板请从学习通下载
  * 实验报告命名方式为：学号_vue1.pdf
  * 代码压缩包方式为：学号_vue1.zip