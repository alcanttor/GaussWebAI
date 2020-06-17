import { NgModule } from '@angular/core';

import { FeatherModule } from 'angular-feather';
import {
  Plus,
  Edit2,
  Trash2,
  MoreVertical,
  Menu,
  Search,
  Home,
  Clock,
  Mail,
  X,
  Check,
} from 'angular-feather/icons';

// Select some icons (use an object, not an array)
const icons = {
  Plus,
  Edit2,
  Trash2,
  MoreVertical,
  Menu,
  Search,
  Home,
  Clock,
  Mail,
  X,
  Check,
};

@NgModule({
  imports: [FeatherModule.pick(icons)],
  exports: [FeatherModule],
})
export class IconsModule {}
