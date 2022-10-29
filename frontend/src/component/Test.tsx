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
    //TODO только пересечения, если объект не полностью на картинке, то он тоже должен туда попасть ??
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
  useEffect(() => {
    if (canvas) {
      canvas.on({
        'object:moving': onCanvasChange,
      });
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
    fabric.Image.fromURL('../img/mrt.jpeg', function (img) {
      console.log('img', img.width)
      console.log('img', img.height)
      img.set({ myId: 'myimg' })
      // img.myId = 'myimg'
      canvas.add(img);
      canvas.centerObject(img);
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
  const getObjects = () => {
    canvas.getObjects().forEach(function (o) {
      console.log(o)
    });
  }
  return <div>
    <button onClick={addRect}>add Rect</button>
    <button onClick={loadImage}>load Image</button>
    <button onClick={getObjects}>Вывести все объекты</button>
    <button onClick={getInterceptions}>Получить разметку на картинке</button>
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
 * блокировать перемещение фигур за картинку - вщту
 * привязывать координаты фигур к картинке
 * блокировать передвижение картинки? только rotate?
 * фигуры всегда выше картинки (zIndex)
 */