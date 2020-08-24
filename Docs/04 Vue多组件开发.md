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
* 

