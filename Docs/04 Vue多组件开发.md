# Vue 多组件开发

[toc]

## 基本示例

```vue
<!-- Counter.vue: 定义 counter 组件-->
<template>
  <button @click="inc">我被点击了 {{ count }} 次</button>
</template>
<script>
  export default {
    data() { return { count: 0 }},
    methods: {
      inc() { this.count++; }
    }
  }
</script>
```

```vue
<!-- App.vue -->
<template>
  <div>
    <counter style="margin-right: 5px;"/>
    <counter />
  </div>
</template>
<script>
  import Counter from './Counter';
  
  export defaults {
    components: { Counter }
  }
</script>
```

<img src="images\Counter 组件示例.png" style="zoom:67%;" />

* 可以复用
* 增强语义：`<div id="counter">` VS `<counter >`

## 路由：Vue-Router

* 没有路由之前：通过组合组件来组成应用

```vue
<template>
  <div>
    <div id="navbar">
       <button @click="select('about')">关于我</button>
       <button @click="select('learn')">旅游日记</button>
    </div>
    <div>
      <about v-if="selected === 'about'" />
      <travel v-if="selected === 'travel'" />
    </div>
  </div>
</template>
<script>
  export default {
    data() { return { selected: 'about' }},
    methods: {
      select(value) { this.selected === value; }
    }
  }
</script>
```

* 有了组件之后：将组件映射到路由，然后告诉 Vue Router 在哪里渲染这些组件
  * 安装 vue-router 库，在工程根目录下执行
    * `yarn add vue-router` 或者
    * `npm install --Save vue-router`

```vue
<template>
  <div>
    <div id="navbar">
      <!-- 使用 router-link 组件来导航 -->
      <!-- 使用 to 属性指定去链接 -->
      <router-link to="/about">关于我</router-link>
      <router-link to="/travel">旅游日记</router-link>
    </div>
    <div>
      <!-- 路由出口 -->
      <!-- 路由匹配的组件将渲染到这里 -->
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
  import About from './components/about'
  import Travel from './components/travel'
  import VueRouter from './vue-router'
  
  const routes = [
    { path: '/about', component: About },
    { path: '/travel', component: Travel }
  ]
  
  const router = new VueRouter({ routes })
  
</script>
```

### HTML5 History模式

* `vue-router` 默认使用 hash 模式，使用 URL 的 hash 模拟一个完整的 URL

  * hash：表示网页中的一个位置，设置方法为
    * `<div id="print">...</div>`
    * `<a href="#print">定位到 print 位置</a>`
    * 当点击锚点时，不会向服务器发送任何请求
  * 当 URL 改变时，页面不会重新加载

* 可以用 **history 模式** 替代 **hash 模式**

  ```javascript
  const router = new VueRouter({
    mode: 'history',
    routes: [...]
  })
  ```

  * history 模式下的 URL 更像正常的 url

## 状态管理：Vuex

* Vuex 是一个专为 Vue.js 应用开发的状态管理模式
* 采用集中式存储管理应用所有组件的状态

### 状态管理模式

* 先看一个简单的计数应用

  ```vue
  <!-- view -->
  <template>
    <div>{{ count }}</div>
  </template>
  <script>
    export default {
      // state
      data() { return { count: 0}; },
      // actions
      methods: {
        inc() { this.count++; }
      }
    }
  </script>
  ```

* Vue 应用一般包括

  * state：驱动应用的数据
  * view：以声明的方式将 state 映射到视图
  * actions：响应在 view 上的用户输入导致的状态变化

* 对于简单的应用【单组件应用及简单的多组件应用】，数据流很好管理

<img src="images\单向数据流.png" style="zoom:60%;" />

* 但是，当应用遇到 **多个组件共享同一状态** 时
  * 多个视图依赖于同一状态
    * 参数会经过多层传递才能到达底层组件
    * 对于兄弟组件之间的状态无能为力
  * 来自不同视图的行为需要变更同一状态
    * 可以通过事件来变更与同步状态，但维护性差
* 所以，可以考虑**把不同组件共享的状态抽取出来，以一个全局单例的方式进行管理**
  * 组件树是一个巨大的视图
  * 组件树上的任何一个节点都可以读取状态并触发行为
* 适用情形
  * 大型单页应用
  * 对于简单的应用，可以考虑 **store 模式**

### Store 模式

* 把所有共享的状态都放在 state 对象中
* 所有的共享状态的操作都放在 store 对象中

```javascript
var store = {
  debug: true,
  state: {
    message: 'Hello!'
  },
  setMessageAction (newValue) {
    if (this.debug) console.log('setMessageAction triggered with', newValue)
    this.state.message = newValue
  },
  clearMessageAction () {
    if (this.debug) console.log('clearMessageAction triggered')
    this.state.message = ''
  }
}
```

* 组件可以使用共享状态，也可以有自己的私有状态

  <img src="images\store 模式.png" style="zoom:60%;" />

  * 组件不能直接修改共享状态，而应执行 actions 来分发事件通知 store 去改变

### Vuex 核心概念：State

* **单一状态树**：每个应用仅包含唯一一个 store 实例

  * 保持响应式：一旦 store 中的状态发生变化，将更新所有组件的 DOM

* 在 Vue 组件中获得 Vuex 状态

  * 使用计算属性

    * **问题**：每个组件都直接依赖了全局状态

    ```vue
    const Counter = {
      template: `<div>{{ count}}</div>`,
      computed: { count() { return store.state.count; } }
    }
    ```

  * 从根组件向子组件注入状态

    * 从根组件注册 store：`const app = new Vue({ store })`
    * 子组件中通过 `this.$store` 进行访问
    * **问题**：当一个组件需要多个状态时，这些计算属性会显得重复和冗余

    ```vue
    const Counter = {
      template: `<div>{{ count}}</div>`,
      computed: { count() { return this.$store.state.count; } }
    }
    ```

  * 使用 `mapState` 函数

    ```javascript
    import { mapState } from 'vuex'
    
    const Counter = {
      template: `<div>{{ count}}</div>`,
      computed: mapState({
        count: state => state.count
      })
    }
    ```

    * 如果计算属性的名称与 state 的属性名相同

    ```javascript
    const Counter = {
      template: `<div>{{ count}}</div>`,
      computed: mapState([
        // 映射 this.count 到 store.state.count
        'count'
      ])
    }
    ```

    * mapState 函数返回的是一个对象，如何与局部计算属性混合使用呢
      * 使用扩展运算符

    ```javascript
    const Counter = {
      template: `<div>{{ count}}</div>`,
      computed: {
        ...mapState([
          // 映射 this.count 到 store.state.count
          'count'
        ]),
        localComputed() { ... }
      }
    }
    ```

### Vuex 核心概念：Getter

* 从 store 的 state 中派生出一些状态

  ```javascript
  computed: {
    doneTodosCount() {
       return this.$store.state.todos.filter(todo => todo.done).length;
    }
  }
  ```

  