<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="commonTableField">
        <h2 class="jh-entity-heading">
          <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.detail.title')">CommonTableField</span>
          {{ commonTableField.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.id')">ID</span>
          </dt>
          <dd>
            <span>{{ commonTableField.id }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.title')">Title</span>
          </dt>
          <dd>
            <span>{{ commonTableField.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.entityFieldName')">Entity Field Name</span>
          </dt>
          <dd>
            <span>{{ commonTableField.entityFieldName }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.type')">Type</span>
          </dt>
          <dd>
            <span v-text="$t('jhipsterSampleApplicationApp.CommonFieldType.' + commonTableField.type)">{{ commonTableField.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.tableColumnName')">Table Column Name</span>
          </dt>
          <dd>
            <span>{{ commonTableField.tableColumnName }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.columnWidth')">Column Width</span>
          </dt>
          <dd>
            <span>{{ commonTableField.columnWidth }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.order')">Order</span>
          </dt>
          <dd>
            <span>{{ commonTableField.order }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.editInList')">Edit In List</span>
          </dt>
          <dd>
            <span>{{ commonTableField.editInList }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.hideInList')">Hide In List</span>
          </dt>
          <dd>
            <span>{{ commonTableField.hideInList }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.hideInForm')">Hide In Form</span>
          </dt>
          <dd>
            <span>{{ commonTableField.hideInForm }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.enableFilter')">Enable Filter</span>
          </dt>
          <dd>
            <span>{{ commonTableField.enableFilter }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.validateRules')">Validate Rules</span>
          </dt>
          <dd>
            <span>{{ commonTableField.validateRules }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.showInFilterTree')">Show In Filter Tree</span>
          </dt>
          <dd>
            <span>{{ commonTableField.showInFilterTree }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.fixed')">Fixed</span>
          </dt>
          <dd>
            <span v-text="$t('jhipsterSampleApplicationApp.FixedType.' + commonTableField.fixed)">{{ commonTableField.fixed }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.sortable')">Sortable</span>
          </dt>
          <dd>
            <span>{{ commonTableField.sortable }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.treeIndicator')">Tree Indicator</span>
          </dt>
          <dd>
            <span>{{ commonTableField.treeIndicator }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.clientReadOnly')">Client Read Only</span>
          </dt>
          <dd>
            <span>{{ commonTableField.clientReadOnly }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.fieldValues')">Field Values</span>
          </dt>
          <dd>
            <span>{{ commonTableField.fieldValues }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.notNull')">Not Null</span>
          </dt>
          <dd>
            <span>{{ commonTableField.notNull }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.system')">System</span>
          </dt>
          <dd>
            <span>{{ commonTableField.system }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.help')">Help</span>
          </dt>
          <dd>
            <span>{{ commonTableField.help }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.fontColor')">Font Color</span>
          </dt>
          <dd>
            <span>{{ commonTableField.fontColor }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.backgroundColor')">Background Color</span>
          </dt>
          <dd>
            <span>{{ commonTableField.backgroundColor }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.nullHideInForm')">Null Hide In Form</span>
          </dt>
          <dd>
            <span>{{ commonTableField.nullHideInForm }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.endUsed')">End Used</span>
          </dt>
          <dd>
            <span v-text="$t('jhipsterSampleApplicationApp.EndUsedType.' + commonTableField.endUsed)">{{ commonTableField.endUsed }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.options')">Options</span>
          </dt>
          <dd>
            <span>{{ commonTableField.options }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.metaModel')">元模型</span>
          </dt>
          <dd>
            <div v-if="commonTableField.metaModelId">
              <router-link :to="{ name: 'CommonTableView', params: { commonTableId: commonTableField.metaModelId } }">{{
                commonTableField.metaModelName
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('jhipsterSampleApplicationApp.modelConfigCommonTableField.commonTable')">所属表</span>
          </dt>
          <dd>
            <div v-if="commonTableField.commonTableId">
              <router-link :to="{ name: 'CommonTableView', params: { commonTableId: commonTableField.commonTableId } }">{{
                commonTableField.commonTableName
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="commonTableField.id"
          :to="{ name: 'CommonTableFieldEdit', params: { commonTableFieldId: commonTableField.id } }"
          tag="button"
          class="btn btn-primary"
        >
          <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./common-table-field-details.component.ts"></script>
