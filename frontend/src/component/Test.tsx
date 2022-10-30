// @ts-nocheck
import { fabric } from 'fabric';
import React, { useEffect, useState } from 'react';
// import api from '../api';

const Editor = () => {
  const [canvas, setCanvas] = useState();
  // const [dicoms, setDicoms] = useState();

  // useEffect(() => {
  //   getDicomImage()

  // }, [])
  // console.log('dicoms', dicoms)

  // const getDicomImage = async () => {
  //   try {
  //     const dicom = await api.dicom.getDicom(111)
  //     console.log('dicom', dicom)
  //     const img = await api.dicom.getDicomImage(dicom.images[0])
  //   } catch (e) {
  //     console.error('getDicom error: ', e)
  //   }
  // }

  const getImage = () => {
    const img = canvas.getObjects().find(function (o) {
      return o.myId === 'myimg'
    });
    return img
  }
  const getInterceptions = () => {
    const img = getImage();
    //TODO только пересечения, если объект не полностью на картинке, то он тоже должен туда попасть ?? updated: пока не попадает, тк запрет на перемещение
    const selectors = canvas.getObjects().filter(function (o) {
      return o.intersectsWithObject(img) && o.myId !== 'myimg'
    });


    const markups = selectors.map(item => {
      const selectorXLeftTop = (item.left - img.left) / img.width
      const selectorYLeftTop = (item.top - img.top) / img.height

      const selectorXRightBottom = (item.left - img.left + item.width) / img.width
      const selectorYRightBottom = (item.top - img.top + item.height) / img.height
      return {
        type: item.type,
        geometry: [[selectorXLeftTop, selectorYLeftTop], [selectorXRightBottom, selectorYRightBottom]]
      }
    })
    console.log('markups', markups)
    console.log(selectors)
  }
  /**
   * Запрет на перемещение фигур вне картинки
   * @param elem 
   */
  const onCanvasChange = (elem) => {
    const img = getImage();
    if (elem.target.myId === 'myimg') {
      //don't work ???
      // elem.lockMovementX = true;
      // elem.lockMovementY = true;
      if (img.top !== elem.target.top) {
        elem.target.top = img.top
      }
      if (img.left !== elem.target.left) {
        elem.target.left = img.left
      }

    } else {


      if (elem.target.top < img.top) {
        elem.target.top = img.top
      }
      if (elem.target.left < img.left) {
        elem.target.left = img.left
      }
      if (elem.target.left + elem.target.width > img.left + img.width) {
        elem.target.left = img.left + img.width - elem.target.width
      }
      if (elem.target.top + elem.target.height > img.top + img.height) {

        elem.target.top = img.top + img.height - elem.target.height
      }
    }
  }
  const onImgChange = (e) => {
    if (e.target.myId === 'myimg') {
      console.log('yep')
    }
  }
  const changeInterceptionCoordinates = () => { }

  function zoom(opt) {
    var delta = opt.e.deltaY;
    var zoom = canvas.getZoom();

    zoom *= 0.999 ** delta;
    if (zoom > 15) zoom = 15;
    if (zoom < 0.5) zoom = 0.5;
    canvas.zoomToPoint({ x: opt.e.offsetX, y: opt.e.offsetY }, zoom);
    opt.e.preventDefault();
    opt.e.stopPropagation();
    //todo ?
    // var vpt = canvas.viewportTransform;
    // if (zoom < 400 / 1000) {
    //   vpt[4] = 200 - 1000 * zoom / 2;
    //   vpt[5] = 200 - 1000 * zoom / 2;
    // } else {
    //   if (vpt[4] >= 0) {
    //     vpt[4] = 0;
    //   } else if (vpt[4] < canvas.getWidth() - 1000 * zoom) {
    //     vpt[4] = canvas.getWidth() - 1000 * zoom;
    //   }
    //   if (vpt[5] >= 0) {
    //     vpt[5] = 0;
    //   } else if (vpt[5] < canvas.getHeight() - 1000 * zoom) {
    //     vpt[5] = canvas.getHeight() - 1000 * zoom;
    //   }
    // }
  }
  function debounce(func, timeout = 10) {
    let timer;
    return (...args) => {
      clearTimeout(timer);
      timer = setTimeout(() => { func.apply(this, args); }, timeout);
    };
  }
  const dZoom = debounce(zoom)
  useEffect(() => {
    if (canvas) {
      //todo debounce
      canvas.on({
        'object:moving': onCanvasChange,
        'object:rotating': onImgChange,
        // 'object:rotate': onImgChange,
      });
      // canvas.on('mouse:wheel', function(opt) {
      //   var delta = opt.e.deltaY;
      //   var zoom = canvas.getZoom();
      //   zoom *= 0.999 ** delta;
      //   if (zoom > 20) zoom = 20;
      //   if (zoom < 0.01) zoom = 0.01;
      //   canvas.setZoom(zoom);
      //   opt.e.preventDefault();
      //   opt.e.stopPropagation();
      // })
      //TODO fix
      canvas.on('mouse:wheel', dZoom)

    }
  }, [canvas])
  useEffect(() => {
    const canvas = new fabric.Canvas("my-fabric-canvas", { preserveObjectStacking: true });

    setCanvas(canvas)

    return () => {
      canvas.dispose();
    };
  }, []);

  const loadImage = () => {
    const img = getImage()
    if (img) return
    fabric.Image.fromURL('../img/mrt.jpeg', function (img) {
      img.set({ myId: 'myimg', lockMovementY: true, lockMovementX: true })
      canvas.centerObject(img);
      canvas.add(img);

      // todo add background image ?
      // canvas.setBackgroundImage(img, canvas.renderAll.bind(canvas), {
      // scaleX: canvas.width / img.width,
      // scaleY: canvas.height / img.height
      // });
    });
  }
  const addRect = () => {
    const img = getImage();
    if (!img) return
    //todo инициализация по центру картинки ?
    canvas.add(new fabric.Rect(
      { top: img.top + 100, left: img.left + 100, width: 100, height: 100, fill: 'rgba(255, 255, 255, 0.0)', stroke: 'red', type: 'rect' }
    ));
  }
  const addCircle = () => {
    const img = getImage();
    if (!img) return
    //todo инициализация по центру картинки ?
    canvas.add(new fabric.Circle(
      { top: img.top + 100, left: img.left + 100, radius: 50, fill: 'rgba(255, 255, 255, 0.0)', stroke: 'green', type: 'circle' }
    ));
  }
  const addLine = () => {
    const img = getImage();
    if (!img) return
    //todo инициализация по центру картинки ?
    canvas.add(new fabric.Line([50, 50, 100, 100], {
      top: img.top + 100,
      left: img.left + 100,
      stroke: 'purple'
    }));
  }
  const addRuler = () => {
    const img = getImage();
    if (!img) return

    canvas.add(
      new fabric.Line([measurementThickness - tickSize, location1, measurementThickness, location1], { stroke: '#888', selectable: false })
    )
    canvas.add(new fabric.Text(count + "\"", {
      left: measurementThickness - (tickSize * 2) - 7,
      top: location1,
      fontSize: 12,
      fontFamily: 'san-serif'
    }))
  }

  const getObjects = () => {
    canvas.getObjects().forEach(function (o) {
      console.log(o)
    });
  }
  const setImgFilters = () => {
    const img = getImage();
    if (img) {
      img.filters = []
      img.filters.push(new fabric.Image.filters.Contrast({ contrast: contrastValue }))
      img.filters.push(new fabric.Image.filters.Brightness({ brightness: brightnessValue }))
      img.applyFilters();
      canvas.requestRenderAll();
    }
  }
  const clearImgFilters = () => {
    setImgContrastValue(0)
    setImgBrightnessValue(0)
  }
  const [contrastValue, setImgContrastValue] = useState(0)
  const [brightnessValue, setImgBrightnessValue] = useState(0)
  const handleRange = (name, e) => {
    if (name === 'contrast') setImgContrastValue(+e.target.value)
    else if (name === 'brightness') setImgBrightnessValue(+e.target.value)
  }
  useEffect(() => {
    if (canvas) {
      setImgFilters()
    }
  }, [brightnessValue, contrastValue])

  return <div>
    <button onClick={addRect}>add Rect</button>
    <button onClick={addCircle}>add Circle</button>
    <button onClick={addLine}>add Line</button>
    {/* <button onClick={addRuler}>add Ruler</button> */}
    <button onClick={loadImage}>load Image</button>
    <button onClick={getObjects}>Вывести все объекты</button>
    <button onClick={getInterceptions}>Получить разметку на картинке</button>
    <button onClick={clearImgFilters}>Сбросить яркость и контраст</button>
    <label>Контраст: <input type="range" min={-0.9} max={0.9} step={0.05} value={contrastValue} onChange={(e) => handleRange('contrast', e)} /></label>
    <label>Яркость:<input type="range" min={-0.9} max={0.9} step={0.05} value={brightnessValue} onChange={(e) => handleRange('brightness', e)} /></label>
    <canvas id="my-fabric-canvas" width="1920px" height="900px" />
  </div>
}

export default Editor

//todo
/**
 * Если мы добавляем новый элемент, то мы должны найти все пересечения (getInterceptions) 
 * и вывести их боковым списком с возможностью добавить мета теги. 
 * Или можно добавлять теги при клике на область, вызывая popover, а в боковой менюшке отображать все это
 */

/**
 * Добавить компонент с запросом списка dicom с бэка и их отображением
 */



/**
 * Образовательная часть (библиотека)
 * Пользователь заходит на вебку и видит боковое меню с категориями голова/легкие или по болезням /categories
 * Далее получает таблицу с поиском и пагинацей
 * 
 * Загрузить новый dicom
 * 
 * Мои dicom
 */

/**
 * Редактор
 * Загрузчик
 * Боковая панель
 * Верхняя панель
 */

/**
 * TODO list
 * блокировать добавление фигур без картинки - done
 * блокировать перемещение фигур за картинку - done
 * привязывать координаты фигур к картинке
 * блокировать передвижение картинки? только rotate? done
 * фигуры всегда выше картинки (zIndex) - done
 * zoomIn, zoomOut - done
 * яркость - done
 * контрастность - done
 * круг
 * линия
 * запретить движение картинки вне канваса - done?
 */
/**
 * наверно надо хранить ссылки на элементы, а не каждый раз за ними ходить в canvas
 */
