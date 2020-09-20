import Vue from 'vue'
import VueRouter from "vue-router";
import Counter from "./components/Counter";
import HelloVueRouter from './components/HelloVueRouter'
import ComponentBasic from "./components/ComponentBasic";
import PassingProperties from "./components/PassingProperties";

Vue.use(VueRouter)

const about = {template: `<template>About Page</template>`}
const travel = {template: '<template>Travel Page</template>'}

const routes = [
    {path: '/counter', component: Counter},
    {
        path: '/hello-router', component: HelloVueRouter,
        children: [
            {path: 'about', component: about},
            {path: 'travel', component: travel}
        ]
    },
    {path: '/basic', component: ComponentBasic},
    {path: '/passProperty', component: PassingProperties}
]

export const router = new VueRouter({routes})
