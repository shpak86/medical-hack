import { FormControl, InputLabel, MenuItem, OutlinedInput, Select, SelectChangeEvent } from "@mui/material";
import styles from "./TagSelector.module.scss";
import { useEffect } from "react";
import { grey } from "@mui/material/colors";
import { DicomImageMarkup } from "../../api/dicom";

interface TagSelectorProps {
  tags?: string[];
  markup?: DicomImageMarkup;
  selectTag: (tags: string[])=> void;
  selectedTag: string[];
}
const defaultTags: string[] = [
  'COVID-19; все доли; многочисленные; размер любой',
  'COVID-19; Нижняя доля правого лёгкого, Нижняя доля левого лёгкого;Немногочисленные; 10-20 мм',
  'Рак лёгкого; Нижняя доля правого лёгкого, Единичное; 10-20 мм',
  'Рак лёгкого; Средняя доля правого лёгкого, Единичное; >20 мм',
  'Рак лёгкого; Нижняя доля левого лёгкого, Единичное; 10-20 мм',
  'Рак лёгкого; Верхняя доля правого лёгкого, Единичное; 5-10 мм',
  'Рак лёгкого; Верхняя доля левого лёгкого, Единичное; 5-10 мм',
  'Метастатическое поражение лёгких; Все доли; Многочисленные; 5-10 мм',
  'Метастатическое поражение лёгких; Все доли; Многочисленные; 10-20 мм',
  'Метастатическое поражение лёгких; Все доли; Немногочисленные; 5-10 мм',
]

export function TagSelector({ tags = defaultTags, markup, selectedTag, selectTag  }: TagSelectorProps) {

  useEffect(() => {
    if (markup && markup.tags && markup.tags.length > 0) {
      selectTag(markup.tags)
    } else {
      selectTag([])
    }
  }, [markup])

  const handleSelect = (e: SelectChangeEvent<typeof defaultTags>) => {
    const { value } = e.target;
    selectTag(typeof value === 'string' ? value.split(',') : value)
  }

  return (
    <div className={styles.root}>
      <FormControl className={styles.form}>
        <InputLabel id="tag-selector">Теги</InputLabel>
        <Select
          labelId="tag-selector"
          id="tag-selector"
          multiple
          value={selectedTag}
          onChange={handleSelect}
          input={<OutlinedInput label="Теги" />}
          sx={{ backgroundColor: grey[200] }}
          size={'small'}
          className={styles.selector}
        >
          {tags.map((tag) => (
            <MenuItem
              key={tag}
              value={tag}
            >
              {tag}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
}
