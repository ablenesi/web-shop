using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.SL.ServiceLayerContracts;
using System.Collections.Generic;
using System.ServiceModel;

namespace WebShop.SL.Client
{
    class CategoryClient : ClientBase<ICategoryService>, ICategoryService
    {
        public IEnumerable<CategoryDTO> GetAll()
        {
            return Channel.GetAll();
        }
    }
}
