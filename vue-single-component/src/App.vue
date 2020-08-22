<template>
  <div>
    <div id="componentDemo">
      <component :is="currentView"></component>
    </div>
    <div id="buttons">

      <button @click="handleChangeView('PureHtml')"
              :class="{'selected': currentView === 'PureHtml'}">
        纯静态内容组件
      </button>
      <button @click="handleChangeView('SimpleTextInterpolation')"
              :class="{'selected': currentView === 'SimpleTextInterpolation'}">
        简单文本插值
      </button>
      <button @click="handleChangeView('JSExpressionInterpolation')"
              :class="{'selected': currentView === 'JSExpressionInterpolation'}">
        JS表达式插值
      </button>
      <button @click="handleChangeView('VBindDirectives')"
              :class="{'selected': currentView === 'VBindDirectives'}">
        v-bind指令
      </button>
      <button @click="handleChangeView('VIfDirectives')"
              :class="{'selected': currentView === 'VIfDirectives'}">
        v-if指令
      </button>
      <button @click="handleChangeView('VElseDirectives')"
              :class="{'selected': currentView === 'VElseDirectives'}">
        v-else指令
      </button>
      <button @click="handleChangeView('VForDirectives')"
              :class="{'selected': currentView === 'VForDirectives'}">
        v-for指令：待办事项
      </button>
      <button @click="handleChangeView('VFor2Directives')"
              :class="{'selected': currentView === 'VFor2Directives'}">
        v-for指令：学生缺勤
      </button>
      <button @click="handleChangeView('VOnDirective')"
              :class="{'selected': currentView === 'VOnDirective'}">
        v-on指令：表达式逻辑
      </button>
      <button @click="handleChangeView('VOn2Directive')"
              :class="{'selected': currentView === 'VOn2Directive'}">
        v-on指令：处理逻辑封装进方法
      </button>
      <button @click="handleChangeView('VOn3Directive')"
              :class="{'selected': currentView === 'VOn3Directive'}">
        v-on指令：内联方法调用
      </button>
      <button @click="handleChangeView('VOn4Directive')"
              :class="{'selected': currentView === 'VOn4Directive'}">
        v-on指令：内联方法调用2
      </button>
      <button @click="handleChangeView('VModelDirective')"
              :class="{'selected': currentView === 'VModelDirective'}">
        v-model指令：文本框
      </button>
      <button @click="handleChangeView('VModel2Directive')"
              :class="{'selected': currentView === 'VModel2Directive'}">
        v-model指令：多行文本
      </button>
      <button @click="handleChangeView('VModel3Directive')"
              :class="{'selected': currentView === 'VModel3Directive'}">
        v-model指令：选择框（单选）
      </button>
    </div>
  </div>
</template>
<script>
import PureHtml from './Interpolation/PureHtml'
import JSExpressionInterpolation from "./Interpolation/JSExpressionInterpolation";
import SimpleTextInterpolation from "./Interpolation/SimpleTextInterpolation";
import VBindDirectives from "./Directives/VBind";
import VIfDirectives from "./Directives/VIf";
import VElseDirectives from "./Directives/VElse";
import VForDirectives from "./Directives/VFor";
import VFor2Directives from "./Directives/VFor2";
import VOnDirective from "./Directives/VOn";
import VOn2Directive from "./Directives/VOn2";
import VOn3Directive from "./Directives/VOn3"
import VOn4Directive from "./Directives/VOn4"
import VModelDirective from "./Directives/VModel";
import VModel2Directive from "./Directives/VModel2";
import VModel3Directive from "./Directives/VModel3";

export default {
  components: {
    PureHtml,
    SimpleTextInterpolation,
    JSExpressionInterpolation,
    VBindDirectives,
    VIfDirectives,
    VElseDirectives,
    VForDirectives,
    VFor2Directives,
    VOnDirective,
    VOn2Directive,
    VOn3Directive,
    VOn4Directive,
    VModelDirective,
    VModel2Directive,
    VModel3Directive
  },
  data() {
    return ({
      currentView: 'PureHtml'
    });
  },
  methods: {
    handleChangeView: function (component) {
      this.currentView = component;
    }
  }
}
</script>

<style scoped>
button {
  margin-top: 10px;
  margin-right: 5px;
}

#componentDemo {
  width: 500px;
  padding: 20px;
  border: solid black 1px;
  border-radius: 6px;
}

#buttons {
  width: 500px;
}

.selected {
  background-color: beige;
  border-color: beige;
}
</style>
