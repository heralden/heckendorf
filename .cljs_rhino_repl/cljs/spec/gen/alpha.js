// Compiled by ClojureScript 1.9.908 {}
goog.provide('cljs.spec.gen.alpha');
goog.require('cljs.core');
goog.require('cljs.core');

/**
* @constructor
 * @implements {cljs.core.IDeref}
*/
cljs.spec.gen.alpha.LazyVar = (function (f,cached){
this.f = f;
this.cached = cached;
this.cljs$lang$protocol_mask$partition0$ = 32768;
this.cljs$lang$protocol_mask$partition1$ = 0;
});
cljs.spec.gen.alpha.LazyVar.prototype.cljs$core$IDeref$_deref$arity$1 = (function (this$){
var self__ = this;
var this$__$1 = this;
if(!((self__.cached == null))){
return self__.cached;
} else {
var x = self__.f.call(null);
if((x == null)){
} else {
self__.cached = x;
}

return x;
}
});

cljs.spec.gen.alpha.LazyVar.getBasis = (function (){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"f","f",43394975,null),cljs.core.with_meta(new cljs.core.Symbol(null,"cached","cached",-1216707864,null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"mutable","mutable",875778266),true], null))], null);
});

cljs.spec.gen.alpha.LazyVar.cljs$lang$type = true;

cljs.spec.gen.alpha.LazyVar.cljs$lang$ctorStr = "cljs.spec.gen.alpha/LazyVar";

cljs.spec.gen.alpha.LazyVar.cljs$lang$ctorPrWriter = (function (this__16136__auto__,writer__16137__auto__,opt__16138__auto__){
return cljs.core._write.call(null,writer__16137__auto__,"cljs.spec.gen.alpha/LazyVar");
});

cljs.spec.gen.alpha.__GT_LazyVar = (function cljs$spec$gen$alpha$__GT_LazyVar(f,cached){
return (new cljs.spec.gen.alpha.LazyVar(f,cached));
});

cljs.spec.gen.alpha.quick_check_ref = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check.quick_check !== 'undefined')){
return clojure.test.check.quick_check;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check","quick-check","clojure.test.check/quick-check",-810344251,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check","quick-check","clojure.test.check/quick-check",-810344251,null)))," never required"].join('')));
}
}),null));
cljs.spec.gen.alpha.quick_check = (function cljs$spec$gen$alpha$quick_check(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18211 = arguments.length;
var i__16681__auto___18212 = (0);
while(true){
if((i__16681__auto___18212 < len__16680__auto___18211)){
args__16687__auto__.push((arguments[i__16681__auto___18212]));

var G__18213 = (i__16681__auto___18212 + (1));
i__16681__auto___18212 = G__18213;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});

cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic = (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.quick_check_ref),args);
});

cljs.spec.gen.alpha.quick_check.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.quick_check.cljs$lang$applyTo = (function (seq18210){
return cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18210));
});

cljs.spec.gen.alpha.for_all_STAR__ref = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.properties.for_all_STAR_ !== 'undefined')){
return clojure.test.check.properties.for_all_STAR_;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.properties","for-all*","clojure.test.check.properties/for-all*",67088845,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.properties","for-all*","clojure.test.check.properties/for-all*",67088845,null)))," never required"].join('')));
}
}),null));
/**
 * Dynamically loaded clojure.test.check.properties/for-all*.
 */
cljs.spec.gen.alpha.for_all_STAR_ = (function cljs$spec$gen$alpha$for_all_STAR_(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18215 = arguments.length;
var i__16681__auto___18216 = (0);
while(true){
if((i__16681__auto___18216 < len__16680__auto___18215)){
args__16687__auto__.push((arguments[i__16681__auto___18216]));

var G__18217 = (i__16681__auto___18216 + (1));
i__16681__auto___18216 = G__18217;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});

cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic = (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.for_all_STAR__ref),args);
});

cljs.spec.gen.alpha.for_all_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.for_all_STAR_.cljs$lang$applyTo = (function (seq18214){
return cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18214));
});

var g_QMARK__18218 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generator_QMARK_ !== 'undefined')){
return clojure.test.check.generators.generator_QMARK_;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null)))," never required"].join('')));
}
}),null));
var g_18219 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__18218){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generate !== 'undefined')){
return clojure.test.check.generators.generate;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null)))," never required"].join('')));
}
});})(g_QMARK__18218))
,null));
var mkg_18220 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__18218,g_18219){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.__GT_Generator !== 'undefined')){
return clojure.test.check.generators.__GT_Generator;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null)))," never required"].join('')));
}
});})(g_QMARK__18218,g_18219))
,null));
cljs.spec.gen.alpha.generator_QMARK_ = ((function (g_QMARK__18218,g_18219,mkg_18220){
return (function cljs$spec$gen$alpha$generator_QMARK_(x){
return cljs.core.deref.call(null,g_QMARK__18218).call(null,x);
});})(g_QMARK__18218,g_18219,mkg_18220))
;

cljs.spec.gen.alpha.generator = ((function (g_QMARK__18218,g_18219,mkg_18220){
return (function cljs$spec$gen$alpha$generator(gfn){
return cljs.core.deref.call(null,mkg_18220).call(null,gfn);
});})(g_QMARK__18218,g_18219,mkg_18220))
;

/**
 * Generate a single value using generator.
 */
cljs.spec.gen.alpha.generate = ((function (g_QMARK__18218,g_18219,mkg_18220){
return (function cljs$spec$gen$alpha$generate(generator){
return cljs.core.deref.call(null,g_18219).call(null,generator);
});})(g_QMARK__18218,g_18219,mkg_18220))
;
cljs.spec.gen.alpha.delay_impl = (function cljs$spec$gen$alpha$delay_impl(gfnd){
return cljs.spec.gen.alpha.generator.call(null,(function (rnd,size){
return new cljs.core.Keyword(null,"gen","gen",142575302).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,gfnd)).call(null,rnd,size);
}));
});
var g__16773__auto___18240 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.hash_map !== 'undefined')){
return clojure.test.check.generators.hash_map;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/hash-map
 */
cljs.spec.gen.alpha.hash_map = ((function (g__16773__auto___18240){
return (function cljs$spec$gen$alpha$hash_map(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18241 = arguments.length;
var i__16681__auto___18242 = (0);
while(true){
if((i__16681__auto___18242 < len__16680__auto___18241)){
args__16687__auto__.push((arguments[i__16681__auto___18242]));

var G__18243 = (i__16681__auto___18242 + (1));
i__16681__auto___18242 = G__18243;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18240))
;

cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18240){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18240),args);
});})(g__16773__auto___18240))
;

cljs.spec.gen.alpha.hash_map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.hash_map.cljs$lang$applyTo = ((function (g__16773__auto___18240){
return (function (seq18221){
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18221));
});})(g__16773__auto___18240))
;


var g__16773__auto___18244 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.list !== 'undefined')){
return clojure.test.check.generators.list;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/list
 */
cljs.spec.gen.alpha.list = ((function (g__16773__auto___18244){
return (function cljs$spec$gen$alpha$list(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18245 = arguments.length;
var i__16681__auto___18246 = (0);
while(true){
if((i__16681__auto___18246 < len__16680__auto___18245)){
args__16687__auto__.push((arguments[i__16681__auto___18246]));

var G__18247 = (i__16681__auto___18246 + (1));
i__16681__auto___18246 = G__18247;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18244))
;

cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18244){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18244),args);
});})(g__16773__auto___18244))
;

cljs.spec.gen.alpha.list.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.list.cljs$lang$applyTo = ((function (g__16773__auto___18244){
return (function (seq18222){
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18222));
});})(g__16773__auto___18244))
;


var g__16773__auto___18248 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.map !== 'undefined')){
return clojure.test.check.generators.map;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/map
 */
cljs.spec.gen.alpha.map = ((function (g__16773__auto___18248){
return (function cljs$spec$gen$alpha$map(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18249 = arguments.length;
var i__16681__auto___18250 = (0);
while(true){
if((i__16681__auto___18250 < len__16680__auto___18249)){
args__16687__auto__.push((arguments[i__16681__auto___18250]));

var G__18251 = (i__16681__auto___18250 + (1));
i__16681__auto___18250 = G__18251;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18248))
;

cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18248){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18248),args);
});})(g__16773__auto___18248))
;

cljs.spec.gen.alpha.map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.map.cljs$lang$applyTo = ((function (g__16773__auto___18248){
return (function (seq18223){
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18223));
});})(g__16773__auto___18248))
;


var g__16773__auto___18252 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.not_empty !== 'undefined')){
return clojure.test.check.generators.not_empty;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/not-empty
 */
cljs.spec.gen.alpha.not_empty = ((function (g__16773__auto___18252){
return (function cljs$spec$gen$alpha$not_empty(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18253 = arguments.length;
var i__16681__auto___18254 = (0);
while(true){
if((i__16681__auto___18254 < len__16680__auto___18253)){
args__16687__auto__.push((arguments[i__16681__auto___18254]));

var G__18255 = (i__16681__auto___18254 + (1));
i__16681__auto___18254 = G__18255;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18252))
;

cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18252){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18252),args);
});})(g__16773__auto___18252))
;

cljs.spec.gen.alpha.not_empty.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.not_empty.cljs$lang$applyTo = ((function (g__16773__auto___18252){
return (function (seq18224){
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18224));
});})(g__16773__auto___18252))
;


var g__16773__auto___18256 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.set !== 'undefined')){
return clojure.test.check.generators.set;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/set
 */
cljs.spec.gen.alpha.set = ((function (g__16773__auto___18256){
return (function cljs$spec$gen$alpha$set(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18257 = arguments.length;
var i__16681__auto___18258 = (0);
while(true){
if((i__16681__auto___18258 < len__16680__auto___18257)){
args__16687__auto__.push((arguments[i__16681__auto___18258]));

var G__18259 = (i__16681__auto___18258 + (1));
i__16681__auto___18258 = G__18259;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18256))
;

cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18256){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18256),args);
});})(g__16773__auto___18256))
;

cljs.spec.gen.alpha.set.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.set.cljs$lang$applyTo = ((function (g__16773__auto___18256){
return (function (seq18225){
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18225));
});})(g__16773__auto___18256))
;


var g__16773__auto___18260 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector !== 'undefined')){
return clojure.test.check.generators.vector;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector
 */
cljs.spec.gen.alpha.vector = ((function (g__16773__auto___18260){
return (function cljs$spec$gen$alpha$vector(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18261 = arguments.length;
var i__16681__auto___18262 = (0);
while(true){
if((i__16681__auto___18262 < len__16680__auto___18261)){
args__16687__auto__.push((arguments[i__16681__auto___18262]));

var G__18263 = (i__16681__auto___18262 + (1));
i__16681__auto___18262 = G__18263;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18260))
;

cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18260){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18260),args);
});})(g__16773__auto___18260))
;

cljs.spec.gen.alpha.vector.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector.cljs$lang$applyTo = ((function (g__16773__auto___18260){
return (function (seq18226){
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18226));
});})(g__16773__auto___18260))
;


var g__16773__auto___18264 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector_distinct !== 'undefined')){
return clojure.test.check.generators.vector_distinct;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector-distinct
 */
cljs.spec.gen.alpha.vector_distinct = ((function (g__16773__auto___18264){
return (function cljs$spec$gen$alpha$vector_distinct(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18265 = arguments.length;
var i__16681__auto___18266 = (0);
while(true){
if((i__16681__auto___18266 < len__16680__auto___18265)){
args__16687__auto__.push((arguments[i__16681__auto___18266]));

var G__18267 = (i__16681__auto___18266 + (1));
i__16681__auto___18266 = G__18267;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18264))
;

cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18264){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18264),args);
});})(g__16773__auto___18264))
;

cljs.spec.gen.alpha.vector_distinct.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector_distinct.cljs$lang$applyTo = ((function (g__16773__auto___18264){
return (function (seq18227){
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18227));
});})(g__16773__auto___18264))
;


var g__16773__auto___18268 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.fmap !== 'undefined')){
return clojure.test.check.generators.fmap;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/fmap
 */
cljs.spec.gen.alpha.fmap = ((function (g__16773__auto___18268){
return (function cljs$spec$gen$alpha$fmap(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18269 = arguments.length;
var i__16681__auto___18270 = (0);
while(true){
if((i__16681__auto___18270 < len__16680__auto___18269)){
args__16687__auto__.push((arguments[i__16681__auto___18270]));

var G__18271 = (i__16681__auto___18270 + (1));
i__16681__auto___18270 = G__18271;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18268))
;

cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18268){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18268),args);
});})(g__16773__auto___18268))
;

cljs.spec.gen.alpha.fmap.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.fmap.cljs$lang$applyTo = ((function (g__16773__auto___18268){
return (function (seq18228){
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18228));
});})(g__16773__auto___18268))
;


var g__16773__auto___18272 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.elements !== 'undefined')){
return clojure.test.check.generators.elements;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/elements
 */
cljs.spec.gen.alpha.elements = ((function (g__16773__auto___18272){
return (function cljs$spec$gen$alpha$elements(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18273 = arguments.length;
var i__16681__auto___18274 = (0);
while(true){
if((i__16681__auto___18274 < len__16680__auto___18273)){
args__16687__auto__.push((arguments[i__16681__auto___18274]));

var G__18275 = (i__16681__auto___18274 + (1));
i__16681__auto___18274 = G__18275;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18272))
;

cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18272){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18272),args);
});})(g__16773__auto___18272))
;

cljs.spec.gen.alpha.elements.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.elements.cljs$lang$applyTo = ((function (g__16773__auto___18272){
return (function (seq18229){
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18229));
});})(g__16773__auto___18272))
;


var g__16773__auto___18276 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.bind !== 'undefined')){
return clojure.test.check.generators.bind;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/bind
 */
cljs.spec.gen.alpha.bind = ((function (g__16773__auto___18276){
return (function cljs$spec$gen$alpha$bind(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18277 = arguments.length;
var i__16681__auto___18278 = (0);
while(true){
if((i__16681__auto___18278 < len__16680__auto___18277)){
args__16687__auto__.push((arguments[i__16681__auto___18278]));

var G__18279 = (i__16681__auto___18278 + (1));
i__16681__auto___18278 = G__18279;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18276))
;

cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18276){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18276),args);
});})(g__16773__auto___18276))
;

cljs.spec.gen.alpha.bind.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.bind.cljs$lang$applyTo = ((function (g__16773__auto___18276){
return (function (seq18230){
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18230));
});})(g__16773__auto___18276))
;


var g__16773__auto___18280 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.choose !== 'undefined')){
return clojure.test.check.generators.choose;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/choose
 */
cljs.spec.gen.alpha.choose = ((function (g__16773__auto___18280){
return (function cljs$spec$gen$alpha$choose(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18281 = arguments.length;
var i__16681__auto___18282 = (0);
while(true){
if((i__16681__auto___18282 < len__16680__auto___18281)){
args__16687__auto__.push((arguments[i__16681__auto___18282]));

var G__18283 = (i__16681__auto___18282 + (1));
i__16681__auto___18282 = G__18283;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18280))
;

cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18280){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18280),args);
});})(g__16773__auto___18280))
;

cljs.spec.gen.alpha.choose.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.choose.cljs$lang$applyTo = ((function (g__16773__auto___18280){
return (function (seq18231){
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18231));
});})(g__16773__auto___18280))
;


var g__16773__auto___18284 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.one_of !== 'undefined')){
return clojure.test.check.generators.one_of;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/one-of
 */
cljs.spec.gen.alpha.one_of = ((function (g__16773__auto___18284){
return (function cljs$spec$gen$alpha$one_of(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18285 = arguments.length;
var i__16681__auto___18286 = (0);
while(true){
if((i__16681__auto___18286 < len__16680__auto___18285)){
args__16687__auto__.push((arguments[i__16681__auto___18286]));

var G__18287 = (i__16681__auto___18286 + (1));
i__16681__auto___18286 = G__18287;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18284))
;

cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18284){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18284),args);
});})(g__16773__auto___18284))
;

cljs.spec.gen.alpha.one_of.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.one_of.cljs$lang$applyTo = ((function (g__16773__auto___18284){
return (function (seq18232){
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18232));
});})(g__16773__auto___18284))
;


var g__16773__auto___18288 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.such_that !== 'undefined')){
return clojure.test.check.generators.such_that;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/such-that
 */
cljs.spec.gen.alpha.such_that = ((function (g__16773__auto___18288){
return (function cljs$spec$gen$alpha$such_that(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18289 = arguments.length;
var i__16681__auto___18290 = (0);
while(true){
if((i__16681__auto___18290 < len__16680__auto___18289)){
args__16687__auto__.push((arguments[i__16681__auto___18290]));

var G__18291 = (i__16681__auto___18290 + (1));
i__16681__auto___18290 = G__18291;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18288))
;

cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18288){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18288),args);
});})(g__16773__auto___18288))
;

cljs.spec.gen.alpha.such_that.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.such_that.cljs$lang$applyTo = ((function (g__16773__auto___18288){
return (function (seq18233){
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18233));
});})(g__16773__auto___18288))
;


var g__16773__auto___18292 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.tuple !== 'undefined')){
return clojure.test.check.generators.tuple;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/tuple
 */
cljs.spec.gen.alpha.tuple = ((function (g__16773__auto___18292){
return (function cljs$spec$gen$alpha$tuple(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18293 = arguments.length;
var i__16681__auto___18294 = (0);
while(true){
if((i__16681__auto___18294 < len__16680__auto___18293)){
args__16687__auto__.push((arguments[i__16681__auto___18294]));

var G__18295 = (i__16681__auto___18294 + (1));
i__16681__auto___18294 = G__18295;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18292))
;

cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18292){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18292),args);
});})(g__16773__auto___18292))
;

cljs.spec.gen.alpha.tuple.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.tuple.cljs$lang$applyTo = ((function (g__16773__auto___18292){
return (function (seq18234){
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18234));
});})(g__16773__auto___18292))
;


var g__16773__auto___18296 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.sample !== 'undefined')){
return clojure.test.check.generators.sample;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/sample
 */
cljs.spec.gen.alpha.sample = ((function (g__16773__auto___18296){
return (function cljs$spec$gen$alpha$sample(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18297 = arguments.length;
var i__16681__auto___18298 = (0);
while(true){
if((i__16681__auto___18298 < len__16680__auto___18297)){
args__16687__auto__.push((arguments[i__16681__auto___18298]));

var G__18299 = (i__16681__auto___18298 + (1));
i__16681__auto___18298 = G__18299;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18296))
;

cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18296){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18296),args);
});})(g__16773__auto___18296))
;

cljs.spec.gen.alpha.sample.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.sample.cljs$lang$applyTo = ((function (g__16773__auto___18296){
return (function (seq18235){
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18235));
});})(g__16773__auto___18296))
;


var g__16773__auto___18300 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.return$ !== 'undefined')){
return clojure.test.check.generators.return$;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/return
 */
cljs.spec.gen.alpha.return$ = ((function (g__16773__auto___18300){
return (function cljs$spec$gen$alpha$return(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18301 = arguments.length;
var i__16681__auto___18302 = (0);
while(true){
if((i__16681__auto___18302 < len__16680__auto___18301)){
args__16687__auto__.push((arguments[i__16681__auto___18302]));

var G__18303 = (i__16681__auto___18302 + (1));
i__16681__auto___18302 = G__18303;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18300))
;

cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18300){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18300),args);
});})(g__16773__auto___18300))
;

cljs.spec.gen.alpha.return$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.return$.cljs$lang$applyTo = ((function (g__16773__auto___18300){
return (function (seq18236){
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18236));
});})(g__16773__auto___18300))
;


var g__16773__auto___18304 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer_STAR_ !== 'undefined')){
return clojure.test.check.generators.large_integer_STAR_;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/large-integer*
 */
cljs.spec.gen.alpha.large_integer_STAR_ = ((function (g__16773__auto___18304){
return (function cljs$spec$gen$alpha$large_integer_STAR_(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18305 = arguments.length;
var i__16681__auto___18306 = (0);
while(true){
if((i__16681__auto___18306 < len__16680__auto___18305)){
args__16687__auto__.push((arguments[i__16681__auto___18306]));

var G__18307 = (i__16681__auto___18306 + (1));
i__16681__auto___18306 = G__18307;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18304))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18304){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18304),args);
});})(g__16773__auto___18304))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$applyTo = ((function (g__16773__auto___18304){
return (function (seq18237){
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18237));
});})(g__16773__auto___18304))
;


var g__16773__auto___18308 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double_STAR_ !== 'undefined')){
return clojure.test.check.generators.double_STAR_;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/double*
 */
cljs.spec.gen.alpha.double_STAR_ = ((function (g__16773__auto___18308){
return (function cljs$spec$gen$alpha$double_STAR_(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18309 = arguments.length;
var i__16681__auto___18310 = (0);
while(true){
if((i__16681__auto___18310 < len__16680__auto___18309)){
args__16687__auto__.push((arguments[i__16681__auto___18310]));

var G__18311 = (i__16681__auto___18310 + (1));
i__16681__auto___18310 = G__18311;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18308))
;

cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18308){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18308),args);
});})(g__16773__auto___18308))
;

cljs.spec.gen.alpha.double_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double_STAR_.cljs$lang$applyTo = ((function (g__16773__auto___18308){
return (function (seq18238){
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18238));
});})(g__16773__auto___18308))
;


var g__16773__auto___18312 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.frequency !== 'undefined')){
return clojure.test.check.generators.frequency;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null)))," never required"].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/frequency
 */
cljs.spec.gen.alpha.frequency = ((function (g__16773__auto___18312){
return (function cljs$spec$gen$alpha$frequency(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18313 = arguments.length;
var i__16681__auto___18314 = (0);
while(true){
if((i__16681__auto___18314 < len__16680__auto___18313)){
args__16687__auto__.push((arguments[i__16681__auto___18314]));

var G__18315 = (i__16681__auto___18314 + (1));
i__16681__auto___18314 = G__18315;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16773__auto___18312))
;

cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16773__auto___18312){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__16773__auto___18312),args);
});})(g__16773__auto___18312))
;

cljs.spec.gen.alpha.frequency.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.frequency.cljs$lang$applyTo = ((function (g__16773__auto___18312){
return (function (seq18239){
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18239));
});})(g__16773__auto___18312))
;

var g__16786__auto___18337 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any !== 'undefined')){
return clojure.test.check.generators.any;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any
 */
cljs.spec.gen.alpha.any = ((function (g__16786__auto___18337){
return (function cljs$spec$gen$alpha$any(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18338 = arguments.length;
var i__16681__auto___18339 = (0);
while(true){
if((i__16681__auto___18339 < len__16680__auto___18338)){
args__16687__auto__.push((arguments[i__16681__auto___18339]));

var G__18340 = (i__16681__auto___18339 + (1));
i__16681__auto___18339 = G__18340;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18337))
;

cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18337){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18337);
});})(g__16786__auto___18337))
;

cljs.spec.gen.alpha.any.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any.cljs$lang$applyTo = ((function (g__16786__auto___18337){
return (function (seq18316){
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18316));
});})(g__16786__auto___18337))
;


var g__16786__auto___18341 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any_printable !== 'undefined')){
return clojure.test.check.generators.any_printable;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any-printable
 */
cljs.spec.gen.alpha.any_printable = ((function (g__16786__auto___18341){
return (function cljs$spec$gen$alpha$any_printable(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18342 = arguments.length;
var i__16681__auto___18343 = (0);
while(true){
if((i__16681__auto___18343 < len__16680__auto___18342)){
args__16687__auto__.push((arguments[i__16681__auto___18343]));

var G__18344 = (i__16681__auto___18343 + (1));
i__16681__auto___18343 = G__18344;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18341))
;

cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18341){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18341);
});})(g__16786__auto___18341))
;

cljs.spec.gen.alpha.any_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any_printable.cljs$lang$applyTo = ((function (g__16786__auto___18341){
return (function (seq18317){
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18317));
});})(g__16786__auto___18341))
;


var g__16786__auto___18345 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.boolean$ !== 'undefined')){
return clojure.test.check.generators.boolean$;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/boolean
 */
cljs.spec.gen.alpha.boolean$ = ((function (g__16786__auto___18345){
return (function cljs$spec$gen$alpha$boolean(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18346 = arguments.length;
var i__16681__auto___18347 = (0);
while(true){
if((i__16681__auto___18347 < len__16680__auto___18346)){
args__16687__auto__.push((arguments[i__16681__auto___18347]));

var G__18348 = (i__16681__auto___18347 + (1));
i__16681__auto___18347 = G__18348;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18345))
;

cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18345){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18345);
});})(g__16786__auto___18345))
;

cljs.spec.gen.alpha.boolean$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.boolean$.cljs$lang$applyTo = ((function (g__16786__auto___18345){
return (function (seq18318){
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18318));
});})(g__16786__auto___18345))
;


var g__16786__auto___18349 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char$ !== 'undefined')){
return clojure.test.check.generators.char$;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char
 */
cljs.spec.gen.alpha.char$ = ((function (g__16786__auto___18349){
return (function cljs$spec$gen$alpha$char(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18350 = arguments.length;
var i__16681__auto___18351 = (0);
while(true){
if((i__16681__auto___18351 < len__16680__auto___18350)){
args__16687__auto__.push((arguments[i__16681__auto___18351]));

var G__18352 = (i__16681__auto___18351 + (1));
i__16681__auto___18351 = G__18352;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18349))
;

cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18349){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18349);
});})(g__16786__auto___18349))
;

cljs.spec.gen.alpha.char$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char$.cljs$lang$applyTo = ((function (g__16786__auto___18349){
return (function (seq18319){
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18319));
});})(g__16786__auto___18349))
;


var g__16786__auto___18353 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alpha !== 'undefined')){
return clojure.test.check.generators.char_alpha;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alpha
 */
cljs.spec.gen.alpha.char_alpha = ((function (g__16786__auto___18353){
return (function cljs$spec$gen$alpha$char_alpha(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18354 = arguments.length;
var i__16681__auto___18355 = (0);
while(true){
if((i__16681__auto___18355 < len__16680__auto___18354)){
args__16687__auto__.push((arguments[i__16681__auto___18355]));

var G__18356 = (i__16681__auto___18355 + (1));
i__16681__auto___18355 = G__18356;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18353))
;

cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18353){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18353);
});})(g__16786__auto___18353))
;

cljs.spec.gen.alpha.char_alpha.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alpha.cljs$lang$applyTo = ((function (g__16786__auto___18353){
return (function (seq18320){
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18320));
});})(g__16786__auto___18353))
;


var g__16786__auto___18357 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alphanumeric !== 'undefined')){
return clojure.test.check.generators.char_alphanumeric;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alphanumeric
 */
cljs.spec.gen.alpha.char_alphanumeric = ((function (g__16786__auto___18357){
return (function cljs$spec$gen$alpha$char_alphanumeric(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18358 = arguments.length;
var i__16681__auto___18359 = (0);
while(true){
if((i__16681__auto___18359 < len__16680__auto___18358)){
args__16687__auto__.push((arguments[i__16681__auto___18359]));

var G__18360 = (i__16681__auto___18359 + (1));
i__16681__auto___18359 = G__18360;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18357))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18357){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18357);
});})(g__16786__auto___18357))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$applyTo = ((function (g__16786__auto___18357){
return (function (seq18321){
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18321));
});})(g__16786__auto___18357))
;


var g__16786__auto___18361 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_ascii !== 'undefined')){
return clojure.test.check.generators.char_ascii;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-ascii
 */
cljs.spec.gen.alpha.char_ascii = ((function (g__16786__auto___18361){
return (function cljs$spec$gen$alpha$char_ascii(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18362 = arguments.length;
var i__16681__auto___18363 = (0);
while(true){
if((i__16681__auto___18363 < len__16680__auto___18362)){
args__16687__auto__.push((arguments[i__16681__auto___18363]));

var G__18364 = (i__16681__auto___18363 + (1));
i__16681__auto___18363 = G__18364;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18361))
;

cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18361){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18361);
});})(g__16786__auto___18361))
;

cljs.spec.gen.alpha.char_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_ascii.cljs$lang$applyTo = ((function (g__16786__auto___18361){
return (function (seq18322){
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18322));
});})(g__16786__auto___18361))
;


var g__16786__auto___18365 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double$ !== 'undefined')){
return clojure.test.check.generators.double$;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/double
 */
cljs.spec.gen.alpha.double$ = ((function (g__16786__auto___18365){
return (function cljs$spec$gen$alpha$double(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18366 = arguments.length;
var i__16681__auto___18367 = (0);
while(true){
if((i__16681__auto___18367 < len__16680__auto___18366)){
args__16687__auto__.push((arguments[i__16681__auto___18367]));

var G__18368 = (i__16681__auto___18367 + (1));
i__16681__auto___18367 = G__18368;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18365))
;

cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18365){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18365);
});})(g__16786__auto___18365))
;

cljs.spec.gen.alpha.double$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double$.cljs$lang$applyTo = ((function (g__16786__auto___18365){
return (function (seq18323){
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18323));
});})(g__16786__auto___18365))
;


var g__16786__auto___18369 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.int$ !== 'undefined')){
return clojure.test.check.generators.int$;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/int
 */
cljs.spec.gen.alpha.int$ = ((function (g__16786__auto___18369){
return (function cljs$spec$gen$alpha$int(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18370 = arguments.length;
var i__16681__auto___18371 = (0);
while(true){
if((i__16681__auto___18371 < len__16680__auto___18370)){
args__16687__auto__.push((arguments[i__16681__auto___18371]));

var G__18372 = (i__16681__auto___18371 + (1));
i__16681__auto___18371 = G__18372;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18369))
;

cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18369){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18369);
});})(g__16786__auto___18369))
;

cljs.spec.gen.alpha.int$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.int$.cljs$lang$applyTo = ((function (g__16786__auto___18369){
return (function (seq18324){
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18324));
});})(g__16786__auto___18369))
;


var g__16786__auto___18373 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword !== 'undefined')){
return clojure.test.check.generators.keyword;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword
 */
cljs.spec.gen.alpha.keyword = ((function (g__16786__auto___18373){
return (function cljs$spec$gen$alpha$keyword(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18374 = arguments.length;
var i__16681__auto___18375 = (0);
while(true){
if((i__16681__auto___18375 < len__16680__auto___18374)){
args__16687__auto__.push((arguments[i__16681__auto___18375]));

var G__18376 = (i__16681__auto___18375 + (1));
i__16681__auto___18375 = G__18376;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18373))
;

cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18373){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18373);
});})(g__16786__auto___18373))
;

cljs.spec.gen.alpha.keyword.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword.cljs$lang$applyTo = ((function (g__16786__auto___18373){
return (function (seq18325){
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18325));
});})(g__16786__auto___18373))
;


var g__16786__auto___18377 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword_ns !== 'undefined')){
return clojure.test.check.generators.keyword_ns;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword-ns
 */
cljs.spec.gen.alpha.keyword_ns = ((function (g__16786__auto___18377){
return (function cljs$spec$gen$alpha$keyword_ns(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18378 = arguments.length;
var i__16681__auto___18379 = (0);
while(true){
if((i__16681__auto___18379 < len__16680__auto___18378)){
args__16687__auto__.push((arguments[i__16681__auto___18379]));

var G__18380 = (i__16681__auto___18379 + (1));
i__16681__auto___18379 = G__18380;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18377))
;

cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18377){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18377);
});})(g__16786__auto___18377))
;

cljs.spec.gen.alpha.keyword_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword_ns.cljs$lang$applyTo = ((function (g__16786__auto___18377){
return (function (seq18326){
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18326));
});})(g__16786__auto___18377))
;


var g__16786__auto___18381 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer !== 'undefined')){
return clojure.test.check.generators.large_integer;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/large-integer
 */
cljs.spec.gen.alpha.large_integer = ((function (g__16786__auto___18381){
return (function cljs$spec$gen$alpha$large_integer(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18382 = arguments.length;
var i__16681__auto___18383 = (0);
while(true){
if((i__16681__auto___18383 < len__16680__auto___18382)){
args__16687__auto__.push((arguments[i__16681__auto___18383]));

var G__18384 = (i__16681__auto___18383 + (1));
i__16681__auto___18383 = G__18384;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18381))
;

cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18381){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18381);
});})(g__16786__auto___18381))
;

cljs.spec.gen.alpha.large_integer.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer.cljs$lang$applyTo = ((function (g__16786__auto___18381){
return (function (seq18327){
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18327));
});})(g__16786__auto___18381))
;


var g__16786__auto___18385 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.ratio !== 'undefined')){
return clojure.test.check.generators.ratio;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/ratio
 */
cljs.spec.gen.alpha.ratio = ((function (g__16786__auto___18385){
return (function cljs$spec$gen$alpha$ratio(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18386 = arguments.length;
var i__16681__auto___18387 = (0);
while(true){
if((i__16681__auto___18387 < len__16680__auto___18386)){
args__16687__auto__.push((arguments[i__16681__auto___18387]));

var G__18388 = (i__16681__auto___18387 + (1));
i__16681__auto___18387 = G__18388;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18385))
;

cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18385){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18385);
});})(g__16786__auto___18385))
;

cljs.spec.gen.alpha.ratio.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.ratio.cljs$lang$applyTo = ((function (g__16786__auto___18385){
return (function (seq18328){
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18328));
});})(g__16786__auto___18385))
;


var g__16786__auto___18389 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type !== 'undefined')){
return clojure.test.check.generators.simple_type;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type
 */
cljs.spec.gen.alpha.simple_type = ((function (g__16786__auto___18389){
return (function cljs$spec$gen$alpha$simple_type(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18390 = arguments.length;
var i__16681__auto___18391 = (0);
while(true){
if((i__16681__auto___18391 < len__16680__auto___18390)){
args__16687__auto__.push((arguments[i__16681__auto___18391]));

var G__18392 = (i__16681__auto___18391 + (1));
i__16681__auto___18391 = G__18392;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18389))
;

cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18389){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18389);
});})(g__16786__auto___18389))
;

cljs.spec.gen.alpha.simple_type.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type.cljs$lang$applyTo = ((function (g__16786__auto___18389){
return (function (seq18329){
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18329));
});})(g__16786__auto___18389))
;


var g__16786__auto___18393 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type_printable !== 'undefined')){
return clojure.test.check.generators.simple_type_printable;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type-printable
 */
cljs.spec.gen.alpha.simple_type_printable = ((function (g__16786__auto___18393){
return (function cljs$spec$gen$alpha$simple_type_printable(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18394 = arguments.length;
var i__16681__auto___18395 = (0);
while(true){
if((i__16681__auto___18395 < len__16680__auto___18394)){
args__16687__auto__.push((arguments[i__16681__auto___18395]));

var G__18396 = (i__16681__auto___18395 + (1));
i__16681__auto___18395 = G__18396;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18393))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18393){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18393);
});})(g__16786__auto___18393))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$applyTo = ((function (g__16786__auto___18393){
return (function (seq18330){
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18330));
});})(g__16786__auto___18393))
;


var g__16786__auto___18397 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string !== 'undefined')){
return clojure.test.check.generators.string;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string
 */
cljs.spec.gen.alpha.string = ((function (g__16786__auto___18397){
return (function cljs$spec$gen$alpha$string(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18398 = arguments.length;
var i__16681__auto___18399 = (0);
while(true){
if((i__16681__auto___18399 < len__16680__auto___18398)){
args__16687__auto__.push((arguments[i__16681__auto___18399]));

var G__18400 = (i__16681__auto___18399 + (1));
i__16681__auto___18399 = G__18400;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18397))
;

cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18397){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18397);
});})(g__16786__auto___18397))
;

cljs.spec.gen.alpha.string.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string.cljs$lang$applyTo = ((function (g__16786__auto___18397){
return (function (seq18331){
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18331));
});})(g__16786__auto___18397))
;


var g__16786__auto___18401 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_ascii !== 'undefined')){
return clojure.test.check.generators.string_ascii;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-ascii
 */
cljs.spec.gen.alpha.string_ascii = ((function (g__16786__auto___18401){
return (function cljs$spec$gen$alpha$string_ascii(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18402 = arguments.length;
var i__16681__auto___18403 = (0);
while(true){
if((i__16681__auto___18403 < len__16680__auto___18402)){
args__16687__auto__.push((arguments[i__16681__auto___18403]));

var G__18404 = (i__16681__auto___18403 + (1));
i__16681__auto___18403 = G__18404;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18401))
;

cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18401){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18401);
});})(g__16786__auto___18401))
;

cljs.spec.gen.alpha.string_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_ascii.cljs$lang$applyTo = ((function (g__16786__auto___18401){
return (function (seq18332){
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18332));
});})(g__16786__auto___18401))
;


var g__16786__auto___18405 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_alphanumeric !== 'undefined')){
return clojure.test.check.generators.string_alphanumeric;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-alphanumeric
 */
cljs.spec.gen.alpha.string_alphanumeric = ((function (g__16786__auto___18405){
return (function cljs$spec$gen$alpha$string_alphanumeric(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18406 = arguments.length;
var i__16681__auto___18407 = (0);
while(true){
if((i__16681__auto___18407 < len__16680__auto___18406)){
args__16687__auto__.push((arguments[i__16681__auto___18407]));

var G__18408 = (i__16681__auto___18407 + (1));
i__16681__auto___18407 = G__18408;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18405))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18405){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18405);
});})(g__16786__auto___18405))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$applyTo = ((function (g__16786__auto___18405){
return (function (seq18333){
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18333));
});})(g__16786__auto___18405))
;


var g__16786__auto___18409 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol !== 'undefined')){
return clojure.test.check.generators.symbol;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol
 */
cljs.spec.gen.alpha.symbol = ((function (g__16786__auto___18409){
return (function cljs$spec$gen$alpha$symbol(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18410 = arguments.length;
var i__16681__auto___18411 = (0);
while(true){
if((i__16681__auto___18411 < len__16680__auto___18410)){
args__16687__auto__.push((arguments[i__16681__auto___18411]));

var G__18412 = (i__16681__auto___18411 + (1));
i__16681__auto___18411 = G__18412;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18409))
;

cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18409){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18409);
});})(g__16786__auto___18409))
;

cljs.spec.gen.alpha.symbol.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol.cljs$lang$applyTo = ((function (g__16786__auto___18409){
return (function (seq18334){
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18334));
});})(g__16786__auto___18409))
;


var g__16786__auto___18413 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol_ns !== 'undefined')){
return clojure.test.check.generators.symbol_ns;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol-ns
 */
cljs.spec.gen.alpha.symbol_ns = ((function (g__16786__auto___18413){
return (function cljs$spec$gen$alpha$symbol_ns(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18414 = arguments.length;
var i__16681__auto___18415 = (0);
while(true){
if((i__16681__auto___18415 < len__16680__auto___18414)){
args__16687__auto__.push((arguments[i__16681__auto___18415]));

var G__18416 = (i__16681__auto___18415 + (1));
i__16681__auto___18415 = G__18416;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18413))
;

cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18413){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18413);
});})(g__16786__auto___18413))
;

cljs.spec.gen.alpha.symbol_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol_ns.cljs$lang$applyTo = ((function (g__16786__auto___18413){
return (function (seq18335){
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18335));
});})(g__16786__auto___18413))
;


var g__16786__auto___18417 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.uuid !== 'undefined')){
return clojure.test.check.generators.uuid;
} else {
throw (new Error(["Var ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null))," does not exist, ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null)))," never required"].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/uuid
 */
cljs.spec.gen.alpha.uuid = ((function (g__16786__auto___18417){
return (function cljs$spec$gen$alpha$uuid(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18418 = arguments.length;
var i__16681__auto___18419 = (0);
while(true){
if((i__16681__auto___18419 < len__16680__auto___18418)){
args__16687__auto__.push((arguments[i__16681__auto___18419]));

var G__18420 = (i__16681__auto___18419 + (1));
i__16681__auto___18419 = G__18420;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});})(g__16786__auto___18417))
;

cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic = ((function (g__16786__auto___18417){
return (function (args){
return cljs.core.deref.call(null,g__16786__auto___18417);
});})(g__16786__auto___18417))
;

cljs.spec.gen.alpha.uuid.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.uuid.cljs$lang$applyTo = ((function (g__16786__auto___18417){
return (function (seq18336){
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18336));
});})(g__16786__auto___18417))
;

/**
 * Returns a generator of a sequence catenated from results of
 * gens, each of which should generate something sequential.
 */
cljs.spec.gen.alpha.cat = (function cljs$spec$gen$alpha$cat(var_args){
var args__16687__auto__ = [];
var len__16680__auto___18423 = arguments.length;
var i__16681__auto___18424 = (0);
while(true){
if((i__16681__auto___18424 < len__16680__auto___18423)){
args__16687__auto__.push((arguments[i__16681__auto___18424]));

var G__18425 = (i__16681__auto___18424 + (1));
i__16681__auto___18424 = G__18425;
continue;
} else {
}
break;
}

var argseq__16688__auto__ = ((((0) < args__16687__auto__.length))?(new cljs.core.IndexedSeq(args__16687__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(argseq__16688__auto__);
});

cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic = (function (gens){
return cljs.spec.gen.alpha.fmap.call(null,(function (p1__18421_SHARP_){
return cljs.core.apply.call(null,cljs.core.concat,p1__18421_SHARP_);
}),cljs.core.apply.call(null,cljs.spec.gen.alpha.tuple,gens));
});

cljs.spec.gen.alpha.cat.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.cat.cljs$lang$applyTo = (function (seq18422){
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq18422));
});

cljs.spec.gen.alpha.qualified_QMARK_ = (function cljs$spec$gen$alpha$qualified_QMARK_(ident){
return !((cljs.core.namespace.call(null,ident) == null));
});
cljs.spec.gen.alpha.gen_builtins = (new cljs.core.Delay((function (){
var simple = cljs.spec.gen.alpha.simple_type_printable.call(null);
return cljs.core.PersistentHashMap.fromArrays([cljs.core.qualified_keyword_QMARK_,cljs.core.seq_QMARK_,cljs.core.vector_QMARK_,cljs.core.any_QMARK_,cljs.core.boolean_QMARK_,cljs.core.char_QMARK_,cljs.core.inst_QMARK_,cljs.core.simple_symbol_QMARK_,cljs.core.sequential_QMARK_,cljs.core.float_QMARK_,cljs.core.set_QMARK_,cljs.core.map_QMARK_,cljs.core.empty_QMARK_,cljs.core.string_QMARK_,cljs.core.double_QMARK_,cljs.core.int_QMARK_,cljs.core.associative_QMARK_,cljs.core.keyword_QMARK_,cljs.core.indexed_QMARK_,cljs.core.zero_QMARK_,cljs.core.simple_keyword_QMARK_,cljs.core.neg_int_QMARK_,cljs.core.nil_QMARK_,cljs.core.ident_QMARK_,cljs.core.qualified_ident_QMARK_,cljs.core.true_QMARK_,cljs.core.integer_QMARK_,cljs.core.nat_int_QMARK_,cljs.core.pos_int_QMARK_,cljs.core.uuid_QMARK_,cljs.core.false_QMARK_,cljs.core.list_QMARK_,cljs.core.simple_ident_QMARK_,cljs.core.number_QMARK_,cljs.core.qualified_symbol_QMARK_,cljs.core.seqable_QMARK_,cljs.core.symbol_QMARK_,cljs.core.coll_QMARK_],[cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.keyword_ns.call(null)),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.any_printable.call(null)], null)),cljs.spec.gen.alpha.boolean$.call(null),cljs.spec.gen.alpha.char$.call(null),cljs.spec.gen.alpha.fmap.call(null,((function (simple){
return (function (p1__18426_SHARP_){
return (new Date(p1__18426_SHARP_));
});})(simple))
,cljs.spec.gen.alpha.large_integer.call(null)),cljs.spec.gen.alpha.symbol.call(null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple)], null)),cljs.spec.gen.alpha.double$.call(null),cljs.spec.gen.alpha.set.call(null,simple),cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.elements.call(null,new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,cljs.core.List.EMPTY,cljs.core.PersistentVector.EMPTY,cljs.core.PersistentArrayMap.EMPTY,cljs.core.PersistentHashSet.EMPTY], null)),cljs.spec.gen.alpha.string_alphanumeric.call(null),cljs.spec.gen.alpha.double$.call(null),cljs.spec.gen.alpha.large_integer.call(null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.vector.call(null,simple)], null)),cljs.spec.gen.alpha.keyword_ns.call(null),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.return$.call(null,(0)),cljs.spec.gen.alpha.keyword.call(null),cljs.spec.gen.alpha.large_integer_STAR_.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"max","max",61366548),(-1)], null)),cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.keyword_ns.call(null),cljs.spec.gen.alpha.symbol_ns.call(null)], null)),cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.keyword_ns.call(null),cljs.spec.gen.alpha.symbol_ns.call(null)], null))),cljs.spec.gen.alpha.return$.call(null,true),cljs.spec.gen.alpha.large_integer.call(null),cljs.spec.gen.alpha.large_integer_STAR_.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"min","min",444991522),(0)], null)),cljs.spec.gen.alpha.large_integer_STAR_.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"min","min",444991522),(1)], null)),cljs.spec.gen.alpha.uuid.call(null),cljs.spec.gen.alpha.return$.call(null,false),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.keyword.call(null),cljs.spec.gen.alpha.symbol.call(null)], null)),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.large_integer.call(null),cljs.spec.gen.alpha.double$.call(null)], null)),cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.symbol_ns.call(null)),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 6, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.set.call(null,simple),cljs.spec.gen.alpha.string_alphanumeric.call(null)], null)),cljs.spec.gen.alpha.symbol_ns.call(null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.set.call(null,simple)], null))]);
}),null));
/**
 * Given a predicate, returns a built-in generator if one exists.
 */
cljs.spec.gen.alpha.gen_for_pred = (function cljs$spec$gen$alpha$gen_for_pred(pred){
if(cljs.core.set_QMARK_.call(null,pred)){
return cljs.spec.gen.alpha.elements.call(null,pred);
} else {
return cljs.core.get.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.gen_builtins),pred);
}
});

//# sourceMappingURL=alpha.js.map
