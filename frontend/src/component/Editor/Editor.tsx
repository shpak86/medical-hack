
const Editor = () => {
  return (
    <div>
      <canvas id="my-fabric-canvas" width={window.innerWidth - 250} height={window.innerHeight}/>
    </div>
  );
};

export default Editor;

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
